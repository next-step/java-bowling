package bowling.domain;

import bowling.domain.State.*;
import bowling.domain.frame.*;
import bowling.dto.FrameResult;
import bowling.dto.FrameResults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest extends FrameTest {

    @Test
    void init() {
        int totalNumberOfFrame = 10;
        Frames frames = Frames.init(totalNumberOfFrame);
        FrameResults result = frames.result();

        List<FrameResult> results = result.results();

        assertThat(results.size()).isEqualTo(totalNumberOfFrame);
        assertThat(frames.currentFrameNumber()).isEqualTo(FrameNumber.first());
    }

    @Test
    void is_done_true() {
        FrameNumber lastFrameNumber = new FrameNumber(4);
        State missState = new Miss(new PinCount(3), new PinCount(4));
        State spareState = new Miss(new PinCount(5), new PinCount(5));
        State strikeState = new Strike();
        FinalState finalState = new FinalState(Arrays.asList(missState), 1);

        FinalFrame finalFrame = FinalFrame.of(lastFrameNumber, finalState);
        NormalFrame normalFrame3 = NormalFrame.of(new FrameNumber(3), missState, finalFrame);
        NormalFrame normalFrame2 = NormalFrame.of(new FrameNumber(2), spareState, normalFrame3);
        NormalFrame normalFrame1 = NormalFrame.of(new FrameNumber(1), strikeState, normalFrame2);

        Frames frames = Frames.from(Arrays.asList(normalFrame1, normalFrame2, normalFrame3, finalFrame));

        assertThat(frames.isDone()).isTrue();
        assertThat(frames.currentFrameNumber()).isEqualTo(lastFrameNumber);
    }

    @Test
    void is_done_false() {
        FrameNumber firstUndoneFrameNumber = new FrameNumber(3);
        State spareState = new Miss(new PinCount(5), new PinCount(5));
        State strikeState = new Strike();

        FinalFrame finalFrame = FinalFrame.from(5);
        NormalFrame normalFrame4 = NormalFrame.of(new FrameNumber(4), new Ready(), finalFrame);
        NormalFrame normalFrame3 = NormalFrame.of(new FrameNumber(3), new Ready(), normalFrame4);
        NormalFrame normalFrame2 = NormalFrame.of(new FrameNumber(2), spareState, normalFrame3);
        NormalFrame normalFrame1 = NormalFrame.of(new FrameNumber(1), strikeState, normalFrame2);

        Frames frames = Frames.from(Arrays.asList(normalFrame4, normalFrame1, normalFrame2, normalFrame3, finalFrame));

        assertThat(frames.isDone()).isFalse();
        assertThat(frames.currentFrameNumber()).isEqualTo(firstUndoneFrameNumber);
    }

    @Test
    @DisplayName("마지막 프레임 직전까지 완료시 결과 테스트")
    void add_pin_count() {
        int firstPinCountOfSecondFrame = 5;
        int secondPinCountOfSecondFrame = 3;

        FinalFrame thirdFinalFrame = FinalFrame.from(3);
        NormalFrame secondUnDoneNormalFrame = NormalFrame.of(new FrameNumber(2), new Ready(), thirdFinalFrame);
        NormalFrame firstStrikeNormalFrame = NormalFrame.of(new FrameNumber(1), new Strike(), secondUnDoneNormalFrame);

        Frames frames = Frames.from(Arrays.asList(firstStrikeNormalFrame, secondUnDoneNormalFrame, thirdFinalFrame));
        frames.addPinCount(firstPinCountOfSecondFrame);
        frames.addPinCount(secondPinCountOfSecondFrame);

        FrameResults result = frames.result();
        List<String> states = result.results().stream()
                .map(FrameResult::state)
                .collect(Collectors.toList());

        assertThat(states).containsExactlyInAnyOrder(STRIKE_SYMBOL, firstPinCountOfSecondFrame + SEPARATOR + secondPinCountOfSecondFrame, EMPTY_SYMBOL);
    }

}
