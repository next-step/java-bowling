package bowling.domain;

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
        FramesResult result = frames.result();

        List<NormalFrameResult> normalFrameResults = result.normalFrameResults();
        FinalFrameResult finalFrameResult = result.finalFrameResult();

        assertThat(normalFrameResults.size()).isEqualTo(totalNumberOfFrame - 1);
        assertThat(finalFrameResult.frameNumber()).isEqualTo(totalNumberOfFrame);
        assertThat(frames.currentFrameNumberInt()).isEqualTo(1);
    }

    @Test
    void is_done_true() {
        int lastFrameNumber = 4;
        List<PinCount> strikePinCount = Arrays.asList(new PinCount(10));
        List<PinCount> sparePinCount = Arrays.asList(new PinCount(5), new PinCount(5));
        List<PinCount> missPinCount = Arrays.asList(new PinCount(3), new PinCount(4));

        NormalFrame normalFrame1 = NormalFrame.of(new FrameNumber(1), strikePinCount);
        NormalFrame normalFrame2 = NormalFrame.of(new FrameNumber(2), sparePinCount);
        NormalFrame normalFrame3 = NormalFrame.of(new FrameNumber(3), missPinCount);
        FinalFrame finalFrame = FinalFrame.of(new FrameNumber(lastFrameNumber),missPinCount);

        Frames frames = Frames.from(Arrays.asList(normalFrame1, normalFrame2, normalFrame3), finalFrame);

        assertThat(frames.isDone()).isTrue();
        assertThat(frames.currentFrameNumberInt()).isEqualTo(lastFrameNumber);
    }

    @Test
    void is_done_false() {
        int firstUndoneFrameNumber = 3;
        List<PinCount> strikePinCount = Arrays.asList(new PinCount(10));
        List<PinCount> sparePinCount = Arrays.asList(new PinCount(5), new PinCount(5));
        List<PinCount> emptyPinCount = new ArrayList<>();

        NormalFrame normalFrame1 = NormalFrame.of(new FrameNumber(1), strikePinCount);
        NormalFrame normalFrame2 = NormalFrame.of(new FrameNumber(2), sparePinCount);
        NormalFrame normalFrame4 = NormalFrame.of(new FrameNumber(4), emptyPinCount);
        NormalFrame normalFrame3 = NormalFrame.of(new FrameNumber(3), emptyPinCount);
        FinalFrame finalFrame = FinalFrame.from(5);

        Frames frames = Frames.from(Arrays.asList(normalFrame4, normalFrame1, normalFrame2, normalFrame3), finalFrame);

        assertThat(frames.isDone()).isFalse();
        assertThat(frames.currentFrameNumberInt()).isEqualTo(firstUndoneFrameNumber);
    }

    @Test
    @DisplayName("처음 첫번째 프레임 종류후 두번째 프레임에 투구완료시 결과 테스트")
    void add_pin_count() {
        int strikePinCount = 10;
        int firstPinCountOfSecondFrame = 5;
        int secondPinCountOfSecondFrame = 3;

        NormalFrame firstStrikeNormalFrame = NormalFrame.of(new FrameNumber(1), Arrays.asList(new PinCount(strikePinCount)));
        NormalFrame secondUnDoneNormalFrame = NormalFrame.of(new FrameNumber(2), new ArrayList<>());
        FinalFrame thirdFinalFrame = FinalFrame.from(3);

        Frames frames = Frames.from(Arrays.asList(firstStrikeNormalFrame, secondUnDoneNormalFrame), thirdFinalFrame);
        frames.addPinCount(firstPinCountOfSecondFrame);
        frames.addPinCount(secondPinCountOfSecondFrame);

        FramesResult result = frames.result();
        List<NormalFrameResult> normalFrameResults = result.normalFrameResults();
        FinalFrameResult finalFrameResult = result.finalFrameResult();

        List<FrameScoreResult> normalFrameScoreResults = normalFrameResults.stream()
                .map(normalFrameResult -> normalFrameResult.frameResult().frameScoreResult())
                .collect(Collectors.toList());
        List<Integer> normalFramePinCounts = normalFrameResults.stream()
                .flatMap(normalFrameResult -> normalFrameResult.frameResult().pinCounts().stream())
                .collect(Collectors.toList());

        List<FrameScoreResult> finalFrameScoreResults = finalFrameResult.frameResults().stream()
                .map(FrameResult::frameScoreResult)
                .collect(Collectors.toList());
        List<Integer> finalFramePinCounts = finalFrameResult.frameResults().stream()
                .flatMap(frameResult -> frameResult.pinCounts().stream())
                .collect(Collectors.toList());


        List<FrameScoreResult> actualTotalFrameScoreResults = Stream.of(normalFrameScoreResults, finalFrameScoreResults)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        List<Integer> actualTotalFramePinCounts = Stream.of(normalFramePinCounts, finalFramePinCounts)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());


        assertThat(actualTotalFrameScoreResults).containsExactlyInAnyOrder(FrameScoreResult.STRIKE, FrameScoreResult.MISS, FrameScoreResult.NONE);
        assertThat(actualTotalFramePinCounts).containsExactlyInAnyOrder(strikePinCount, firstPinCountOfSecondFrame, secondPinCountOfSecondFrame);
        assertThat(frames.currentFrameNumberInt()).isEqualTo(3);
    }


}
