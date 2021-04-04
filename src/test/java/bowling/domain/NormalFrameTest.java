package bowling.domain;

import bowling.dto.FrameResult;
import bowling.dto.FrameScoreResult;
import bowling.dto.NormalFrameResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class NormalFrameTest {

    @Test
    void first() {
        NormalFrame first = NormalFrame.first();
        NormalFrameResult result = first.result();

        assertThat(result.frameNumber()).isEqualTo(1);
    }

    @Test
    void next() {
        NormalFrame nextThree = NormalFrame.first().next().next();
        NormalFrameResult result = nextThree.result();

        assertThat(result.frameNumber()).isEqualTo(3);
    }

    @Test
    void add_pin_count() {
        int frameNumber = 3;
        int pinCount = 6;
        NormalFrame normalFrame = new NormalFrame(frameNumber);
        normalFrame.addPintCount(pinCount);

        NormalFrameResult result = normalFrame.result();

        assertThat(result.frameNumber()).isEqualTo(frameNumber);
        FrameResult frameResult = result.framesResult();
        assertThat(frameResult.frameScoreResult()).isEqualTo(FrameScoreResult.NONE);
        assertThat(frameResult.pinCounts()).containsExactly(pinCount);
    }

    @Test
    void add_pin_counts_when_done_throw_exception() {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.addPintCount(4);
        normalFrame.addPintCount(5);

        assertThat(normalFrame.isDone()).isTrue();
        assertThatIllegalStateException().isThrownBy(() ->
                normalFrame.addPintCount(5));
    }

    @Test
    void result_when_strike() {
        NormalFrame normalFrame = NormalFrame.first().next();
        int strikePinCounts = 10;
        normalFrame.addPintCount(strikePinCounts);

        FrameResult result = normalFrame.result().framesResult();
        assertThat(normalFrame.isDone()).isTrue();
        assertThat(result.frameScoreResult()).isEqualTo(FrameScoreResult.STRIKE);
        assertThat(result.pinCounts()).containsExactly(strikePinCounts);
    }


    @Test
    void result_when_spare() {
        NormalFrame normalFrame = NormalFrame.first().next();
        int firstPinCount = 2;
        int secondPinCount = 8;
        normalFrame.addPintCount(firstPinCount);
        normalFrame.addPintCount(secondPinCount);

        FrameResult result = normalFrame.result().framesResult();
        assertThat(normalFrame.isDone()).isTrue();
        assertThat(result.frameScoreResult()).isEqualTo(FrameScoreResult.SPARE);
        assertThat(result.pinCounts()).containsExactlyInAnyOrder(firstPinCount,secondPinCount);
    }

    @Test
    void result_when_miss() {
        NormalFrame normalFrame = NormalFrame.first();
        int firstPinCount = 2;
        int secondPinCount = 6;
        normalFrame.addPintCount(firstPinCount);
        normalFrame.addPintCount(secondPinCount);

        FrameResult result = normalFrame.result().framesResult();
        assertThat(normalFrame.isDone()).isTrue();
        assertThat(result.frameScoreResult()).isEqualTo(FrameScoreResult.MISS);
        assertThat(result.pinCounts()).containsExactlyInAnyOrder(firstPinCount,secondPinCount);
    }

    @Test
    void result_when_none() {
        NormalFrame normalFrame = NormalFrame.first();
        int firstPinCount = 2;
        normalFrame.addPintCount(firstPinCount);

        FrameResult result = normalFrame.result().framesResult();
        assertThat(normalFrame.isDone()).isFalse();
        assertThat(result.frameScoreResult()).isEqualTo(FrameScoreResult.NONE);
        assertThat(result.pinCounts()).containsExactly(firstPinCount);
    }


}
