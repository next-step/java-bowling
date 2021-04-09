package bowling.domain;

import bowling.domain.State.StateType;
import bowling.domain.frame.*;
import bowling.dto.*;
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
        assertThat(frames.currentFrameNumber()).isEqualTo(1);
    }

    @Test
    void is_done_true() {
        int lastFrameNumber = 4;
        List<PinCount> strikePinCounts = Arrays.asList(new PinCount(10));
        List<PinCount> sparePinCounts = Arrays.asList(new PinCount(5), new PinCount(5));
        List<PinCount> missPinCounts = Arrays.asList(new PinCount(3), new PinCount(4));

        NormalFrame finalBonusFrame1 = NormalFrame.of(new FrameNumber(1), missPinCounts, null);
        FinalFrame finalFrame = FinalFrame.of(new FrameNumber(lastFrameNumber),Arrays.asList(finalBonusFrame1));
        NormalFrame normalFrame3 = NormalFrame.of(new FrameNumber(3), missPinCounts, finalFrame);
        NormalFrame normalFrame2 = NormalFrame.of(new FrameNumber(2),sparePinCounts,normalFrame3);
        NormalFrame normalFrame1 = NormalFrame.of(new FrameNumber(1),strikePinCounts,normalFrame2);

        Frames frames = Frames.from(Arrays.asList(normalFrame1, normalFrame2, normalFrame3, finalFrame));

        assertThat(frames.isDone()).isTrue();
        assertThat(frames.currentFrameNumber()).isEqualTo(lastFrameNumber);
    }

    @Test
    void is_done_false() {
        int firstUndoneFrameNumber = 3;
        List<PinCount> strikePinCount = Arrays.asList(new PinCount(10));
        List<PinCount> sparePinCount = Arrays.asList(new PinCount(5), new PinCount(5));
        List<PinCount> emptyPinCount = new ArrayList<>();

        FinalFrame finalFrame = FinalFrame.from(5);
        NormalFrame normalFrame4 = NormalFrame.of(new FrameNumber(4), emptyPinCount,finalFrame);
        NormalFrame normalFrame3 = NormalFrame.of(new FrameNumber(3), emptyPinCount,normalFrame4);
        NormalFrame normalFrame2 = NormalFrame.of(new FrameNumber(2), sparePinCount,normalFrame3);
        NormalFrame normalFrame1 = NormalFrame.of(new FrameNumber(1), strikePinCount,normalFrame2);

        Frames frames = Frames.from(Arrays.asList(normalFrame4, normalFrame1, normalFrame2, normalFrame3, finalFrame));

        assertThat(frames.isDone()).isFalse();
        assertThat(frames.currentFrameNumber()).isEqualTo(firstUndoneFrameNumber);
    }

    @Test
    @DisplayName("처음 첫번째 프레임 종류후 두번째 프레임에 투구완료시 결과 테스트")
    void add_pin_count() {
        int strikePinCount = 10;
        int firstPinCountOfSecondFrame = 5;
        int secondPinCountOfSecondFrame = 3;

        FinalFrame thirdFinalFrame = FinalFrame.from(3);
        NormalFrame secondUnDoneNormalFrame = NormalFrame.of(new FrameNumber(2), new ArrayList<>(),thirdFinalFrame);
        NormalFrame firstStrikeNormalFrame = NormalFrame.of(new FrameNumber(1), Arrays.asList(new PinCount(strikePinCount)),secondUnDoneNormalFrame);

        Frames frames = Frames.from(Arrays.asList(firstStrikeNormalFrame, secondUnDoneNormalFrame, thirdFinalFrame));
        frames.addPinCount(firstPinCountOfSecondFrame);
        frames.addPinCount(secondPinCountOfSecondFrame);

        FrameResults result = frames.result();
        List<NormalFrameResult> normalFrameResults = result.normalFrameResults();
        FinalFrameResult finalFrameResult = result.finalFrameResult();

        List<StateType> normalFrameScoreResults = normalFrameResults.stream()
                .map(normalFrameResult -> normalFrameResult.pinCountsResult().stateType())
                .collect(Collectors.toList());
        List<Integer> normalFramePinCounts = normalFrameResults.stream()
                .flatMap(normalFrameResult -> normalFrameResult.pinCounts().stream())
                .collect(Collectors.toList());

        List<StateType> finalFrameScoreResults = finalFrameResult.pinCountsResult().stream()
                .map(PinCountsResult::stateType)
                .collect(Collectors.toList());
        List<Integer> finalFramePinCounts = finalFrameResult.pinCounts();


        List<StateType> actualTotalFrameScoreResults = Stream.of(normalFrameScoreResults, finalFrameScoreResults)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        List<Integer> actualTotalFramePinCounts = Stream.of(normalFramePinCounts, finalFramePinCounts)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());


        assertThat(actualTotalFrameScoreResults).containsExactlyInAnyOrder(StateType.STRIKE, StateType.MISS, StateType.NONE);
        assertThat(actualTotalFramePinCounts).containsExactlyInAnyOrder(strikePinCount, firstPinCountOfSecondFrame, secondPinCountOfSecondFrame);
        assertThat(frames.currentFrameNumber()).isEqualTo(3);
    }


}
