package bowling.domain;

import bowling.dto.FrameResult;
import bowling.dto.FrameScoreResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class FrameTest {

    @Test
    void add_pin_count() {
        int pinCount = 6;
        Frame frame = new Frame();
        frame.addPinCount(pinCount);
        FrameResult result = frame.result();

        assertThat(result.frameScoreResult()).isEqualTo(FrameScoreResult.NONE);
        assertThat(result.pinCounts()).containsExactly(pinCount);
    }

    @Test
    void add_pin_count_after_done_throw_exception() {
        Frame frame = new Frame();
        frame.addPinCount(6);
        frame.addPinCount(4);

        assertThat(frame.isDone()).isTrue();
        assertThatIllegalStateException().isThrownBy(() ->
                frame.addPinCount(2));
    }

    @Test
    void add_pin_count_over_max_total_pin_counts_throw_exception() {
        Frame frame = new Frame();
        frame.addPinCount(5);

        assertThatIllegalStateException().isThrownBy(() ->
                frame.addPinCount(9));
    }

    @Test
    void result_when_strike() {
        Frame frame = new Frame();
        int strikePinCounts = 10;
        frame.addPinCount(strikePinCounts);

        FrameResult result = frame.result();
        assertThat(frame.isDone()).isTrue();
        assertThat(result.frameScoreResult()).isEqualTo(FrameScoreResult.STRIKE);
        assertThat(result.pinCounts()).containsExactly(strikePinCounts);
    }


    @Test
    void result_when_spare() {
        Frame frame = new Frame();
        int firstPinCount = 2;
        int secondPinCount = 8;
        frame.addPinCount(firstPinCount);
        frame.addPinCount(secondPinCount);

        FrameResult result = frame.result();
        assertThat(frame.isDone()).isTrue();
        assertThat(result.frameScoreResult()).isEqualTo(FrameScoreResult.SPARE);
        assertThat(result.pinCounts()).containsExactlyInAnyOrder(firstPinCount,secondPinCount);
    }

    @Test
    void result_when_miss() {
        Frame frame = new Frame();
        int firstPinCount = 2;
        int secondPinCount = 6;
        frame.addPinCount(firstPinCount);
        frame.addPinCount(secondPinCount);

        FrameResult result = frame.result();
        assertThat(frame.isDone()).isTrue();
        assertThat(result.frameScoreResult()).isEqualTo(FrameScoreResult.MISS);
        assertThat(result.pinCounts()).containsExactlyInAnyOrder(firstPinCount,secondPinCount);
    }

    @Test
    void result_when_none() {
        Frame frame = new Frame();
        int firstPinCount = 2;
        frame.addPinCount(firstPinCount);

        FrameResult result = frame.result();
        assertThat(frame.isDone()).isFalse();
        assertThat(result.frameScoreResult()).isEqualTo(FrameScoreResult.NONE);
        assertThat(result.pinCounts()).containsExactly(firstPinCount);
    }

    @Test
    void is_match() {
        Frame frame = new Frame();
        int firstPinCount = 2;
        int secondPinCount = 6;
        frame.addPinCount(firstPinCount);
        frame.addPinCount(secondPinCount);
        FrameScoreResult expected = FrameScoreResult.MISS;

        assertThat(frame.isMatch(expected)).isTrue();
    }

}
