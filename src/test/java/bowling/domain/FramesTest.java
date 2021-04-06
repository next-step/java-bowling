package bowling.domain;

import bowling.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Frame strikeFrame = new Frame(Arrays.asList(new PinCount(10)));
        Frame spareFrame = new Frame(Arrays.asList(new PinCount(5), new PinCount(5)));
        Frame missFrame = new Frame(Arrays.asList(new PinCount(3), new PinCount(4)));
        Frame missFrameForFinal = new Frame(Arrays.asList(new PinCount(3), new PinCount(4)));

        NormalFrame normalFrame1 = NormalFrame.from(strikeFrame, new FrameNumber(1));
        NormalFrame normalFrame2 = NormalFrame.from(spareFrame, new FrameNumber(2));
        NormalFrame normalFrame3 = NormalFrame.from(missFrame, new FrameNumber(3));
        FinalFrame finalFrame = FinalFrame.from(Arrays.asList(missFrameForFinal), new FrameNumber(lastFrameNumber));

        Frames frames = Frames.from(Arrays.asList(normalFrame1, normalFrame2, normalFrame3), finalFrame);

        assertThat(frames.isDone()).isTrue();
        assertThat(frames.currentFrameNumberInt()).isEqualTo(lastFrameNumber);
    }

    @Test
    void is_done_false() {
        int firstUndoneFrameNumber = 3;
        Frame strikeFrame = new Frame(Arrays.asList(new PinCount(10)));
        Frame spareFrame = new Frame(Arrays.asList(new PinCount(5), new PinCount(5)));
        Frame unDoneFrame1 = new Frame();
        Frame unDoneFrame2 = new Frame();
        Frame unDoneFrameForFinal = new Frame();

        NormalFrame normalFrame1 = NormalFrame.from(strikeFrame, new FrameNumber(1));
        NormalFrame normalFrame2 = NormalFrame.from(spareFrame, new FrameNumber(2));
        NormalFrame normalFrame4 = NormalFrame.from(unDoneFrame2, new FrameNumber(4));
        NormalFrame normalFrame3 = NormalFrame.from(unDoneFrame1, new FrameNumber(3));
        FinalFrame finalFrame = FinalFrame.from(Arrays.asList(unDoneFrameForFinal), new FrameNumber(5));

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

        NormalFrame firstStrikeNormalFrame = NormalFrame.from(new Frame(Arrays.asList(new PinCount(strikePinCount))), new FrameNumber(1));
        NormalFrame secondUnDoneNormalFrame = NormalFrame.from(new Frame(), new FrameNumber(2));
        FinalFrame thirdFinalFrame = FinalFrame.of(3);

        Frames frames = Frames.from(Arrays.asList(firstStrikeNormalFrame, secondUnDoneNormalFrame), thirdFinalFrame);
        frames.addPinCount(firstPinCountOfSecondFrame);
        frames.addPinCount(secondPinCountOfSecondFrame);

        FramesResult result = frames.result();
        List<NormalFrameResult> normalFrameResults = result.normalFrameResults();
        FinalFrameResult finalFrameResult = result.finalFrameResult();

        List<FrameScoreResult> normalFrameScoreResults = normalFrameResults.stream()
                .map(normalFrameResult -> normalFrameResult.framesResult().frameScoreResult())
                .collect(Collectors.toList());
        List<Integer> normalFramePinCounts = normalFrameResults.stream()
                .flatMap(normalFrameResult -> normalFrameResult.framesResult().pinCounts().stream())
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
