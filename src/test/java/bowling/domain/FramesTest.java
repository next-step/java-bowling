package bowling.domain;

import bowling.domain.State.StateType;
import bowling.domain.frame.*;
import bowling.dto.FinalFrameResult;
import bowling.dto.FrameResults;
import bowling.dto.NormalFrameResult;
import bowling.dto.PinCountsResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    @Test
    void init() {
        int totalNumberOfFrame = 10;
        Frames frames = Frames.init(totalNumberOfFrame);
        FrameResults result = frames.result();

        List<NormalFrameResult> normalFrameResults = result.normalFrameResults();
        FinalFrameResult finalFrameResult = result.finalFrameResult();

        assertThat(normalFrameResults.size()).isEqualTo(totalNumberOfFrame - 1);
        assertThat(finalFrameResult.frameNumber()).isEqualTo(totalNumberOfFrame);
        assertThat(frames.currentFrameNumber()).isEqualTo(FrameNumber.first());
    }

    @Test
    void is_done_true() {
        FrameNumber lastFrameNumber = new FrameNumber(4);
        List<PinCount> strikePinCounts = Arrays.asList(new PinCount(10));
        List<PinCount> sparePinCounts = Arrays.asList(new PinCount(5), new PinCount(5));
        List<PinCount> missPinCounts = Arrays.asList(new PinCount(3), new PinCount(4));

        NormalFrame finalBonusFrame1 = NormalFrame.of(new FrameNumber(1), missPinCounts, null);
        FinalFrame finalFrame = FinalFrame.of(lastFrameNumber, Arrays.asList(finalBonusFrame1));
        NormalFrame normalFrame3 = NormalFrame.of(new FrameNumber(3), missPinCounts, finalFrame);
        NormalFrame normalFrame2 = NormalFrame.of(new FrameNumber(2), sparePinCounts, normalFrame3);
        NormalFrame normalFrame1 = NormalFrame.of(new FrameNumber(1), strikePinCounts, normalFrame2);

        Frames frames = Frames.from(Arrays.asList(normalFrame1, normalFrame2, normalFrame3, finalFrame));

        assertThat(frames.isDone()).isTrue();
        assertThat(frames.currentFrameNumber()).isEqualTo(lastFrameNumber);
    }

    @Test
    void is_done_false() {
        FrameNumber firstUndoneFrameNumber = new FrameNumber(3);
        List<PinCount> strikePinCount = Arrays.asList(new PinCount(10));
        List<PinCount> sparePinCount = Arrays.asList(new PinCount(5), new PinCount(5));
        List<PinCount> emptyPinCount = new ArrayList<>();

        FinalFrame finalFrame = FinalFrame.from(5);
        NormalFrame normalFrame4 = NormalFrame.of(new FrameNumber(4), emptyPinCount, finalFrame);
        NormalFrame normalFrame3 = NormalFrame.of(new FrameNumber(3), emptyPinCount, normalFrame4);
        NormalFrame normalFrame2 = NormalFrame.of(new FrameNumber(2), sparePinCount, normalFrame3);
        NormalFrame normalFrame1 = NormalFrame.of(new FrameNumber(1), strikePinCount, normalFrame2);

        Frames frames = Frames.from(Arrays.asList(normalFrame4, normalFrame1, normalFrame2, normalFrame3, finalFrame));

        assertThat(frames.isDone()).isFalse();
        assertThat(frames.currentFrameNumber()).isEqualTo(firstUndoneFrameNumber);
    }

    @Test
    @DisplayName("마지막 프레임 직전까지 완료시 결과 테스트")
    void add_pin_count() {
        int strikePinCount = 10;
        int firstPinCountOfSecondFrame = 5;
        int secondPinCountOfSecondFrame = 3;

        FinalFrame thirdFinalFrame = FinalFrame.from(3);
        NormalFrame secondUnDoneNormalFrame = NormalFrame.of(new FrameNumber(2), new ArrayList<>(), thirdFinalFrame);
        NormalFrame firstStrikeNormalFrame = NormalFrame.of(new FrameNumber(1), Arrays.asList(new PinCount(strikePinCount)), secondUnDoneNormalFrame);

        Frames frames = Frames.from(Arrays.asList(firstStrikeNormalFrame, secondUnDoneNormalFrame, thirdFinalFrame));
        frames.addPinCount(firstPinCountOfSecondFrame);
        frames.addPinCount(secondPinCountOfSecondFrame);

        FrameResults result = frames.result();

        assertThat(actualTotalFrameScoreResultsFrom(result)).containsExactlyInAnyOrder(StateType.STRIKE, StateType.MISS, StateType.NONE);
        assertThat(actualTotalFramePinCountsFrom(result)).containsExactlyInAnyOrder(strikePinCount, firstPinCountOfSecondFrame, secondPinCountOfSecondFrame);
    }

    private List<StateType> actualTotalFrameScoreResultsFrom(FrameResults result) {
        List<NormalFrameResult> normalFrameResults = result.normalFrameResults();
        FinalFrameResult finalFrameResult = result.finalFrameResult();

        List<StateType> normalFrameScoreResults = normalFrameResults.stream()
                .map(normalFrameResult -> normalFrameResult.pinCountsResult().stateType())
                .collect(Collectors.toList());
        List<StateType> finalFrameScoreResults = finalFrameResult.pinCountsResult().stream()
                .map(PinCountsResult::stateType)
                .collect(Collectors.toList());

        return Stream.of(normalFrameScoreResults, finalFrameScoreResults)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Integer> actualTotalFramePinCountsFrom(FrameResults result) {
        List<NormalFrameResult> normalFrameResults = result.normalFrameResults();
        FinalFrameResult finalFrameResult = result.finalFrameResult();

        List<Integer> normalFramePinCounts = normalFrameResults.stream()
                .flatMap(normalFrameResult -> normalFrameResult.pinCounts().stream())
                .collect(Collectors.toList());
        List<Integer> finalFramePinCounts = finalFrameResult.pinCounts();

        return Stream.of(normalFramePinCounts, finalFramePinCounts)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


}
