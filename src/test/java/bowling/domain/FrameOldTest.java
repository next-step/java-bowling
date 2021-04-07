package bowling.domain;

import bowling.dto.FrameResult;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class FrameOldTest {

    @Test
    void create_from_total_pin_counts_more_than_10_throw_exception() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new FrameOld(Arrays.asList(new PinCount(5),new PinCount(11))));
    }

    @Test
    void create_from_pin_count_list_size_more_than_2_throw_exception() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new FrameOld(Arrays.asList(new PinCount(5),new PinCount(5),new PinCount(5))));
    }

    @Test
    void add_pin_count() {
        int pinCount = 6;
        FrameOld frame = new FrameOld();
        frame.addPinCount(pinCount);
        FrameResult result = frame.result();

        assertThat(result.frameScoreResult()).isEqualTo(FrameScoreResult.NONE);
        assertThat(result.pinCounts()).containsExactly(pinCount);
    }

    @Test
    void add_pin_count_after_done_throw_exception() {
        FrameOld frame = new FrameOld();
        frame.addPinCount(6);
        frame.addPinCount(4);

        assertThat(frame.isDone()).isTrue();
        assertThatIllegalStateException().isThrownBy(() ->
                frame.addPinCount(2));
    }

    @Test
    void add_pin_count_over_max_total_pin_counts_throw_exception() {
        FrameOld frame = new FrameOld();
        frame.addPinCount(5);

        assertThatIllegalArgumentException().isThrownBy(() ->
                frame.addPinCount(9));
    }

    @Test
    void result_when_strike() {
        FrameOld frame = new FrameOld();
        int strikePinCounts = 10;
        frame.addPinCount(strikePinCounts);

        FrameResult result = frame.result();
        assertThat(frame.isDone()).isTrue();
        assertThat(result.frameScoreResult()).isEqualTo(FrameScoreResult.STRIKE);
        assertThat(result.pinCounts()).containsExactly(strikePinCounts);
    }


    @Test
    void result_when_spare() {
        FrameOld frame = new FrameOld();
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
        FrameOld frame = new FrameOld();
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
        FrameOld frame = new FrameOld();
        int firstPinCount = 2;
        frame.addPinCount(firstPinCount);

        FrameResult result = frame.result();
        assertThat(frame.isDone()).isFalse();
        assertThat(result.frameScoreResult()).isEqualTo(FrameScoreResult.NONE);
        assertThat(result.pinCounts()).containsExactly(firstPinCount);
    }

    @Test
    void is_match() {
        FrameOld frame = new FrameOld();
        int firstPinCount = 2;
        int secondPinCount = 6;
        frame.addPinCount(firstPinCount);
        frame.addPinCount(secondPinCount);
        FrameScoreResult expected = FrameScoreResult.MISS;

        assertThat(frame.isMatch(expected)).isTrue();
    }

}
