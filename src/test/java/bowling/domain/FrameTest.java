package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    void first() {
        NormalFrame first = NormalFrame.first();
        assertThat(first.number()).isEqualTo(1);
    }

    @Test
    void next() {
        NormalFrame next = NormalFrame.first().next();
        assertThat(next.number()).isEqualTo(2);
    }

    @Test
    void last() {
        FinalFrame last = NormalFrame.first().last();
        assertThat(last.number()).isEqualTo(2);
    }

    @Test
    void normal_frame_add_pin_counts() {
        int pinCount = 3;
        NormalFrame first = NormalFrame.first();
        first.addPintCount(pinCount);
        List<Integer> pinCounts = first.pinCounts();

        assertThat(pinCounts).containsExactly(pinCount);
    }

    @Test
    void normal_frame_add_pin_counts_when_done_throw_exception() {
        NormalFrame first = NormalFrame.first();

    }

    @Test
    void final_frame_add_pin_counts_when_done_throw_exception() {

        FinalFrame last = NormalFrame.first().last();

    }

    @Test
    @DisplayName("normalFrame 모든 핀을 쓸어뜨리지 못했을때 테스트")
    void normal_frame_is_done_when_miss() {
        NormalFrame normalFrame = new NormalFrame(2);
        normalFrame.addPintCount(4);
        normalFrame.addPintCount(5);

        assertThat(normalFrame.isDone()).isTrue();
    }

    @Test
    @DisplayName("normalFrame 스트라이크 테스트")
    void normal_frame_is_done_when_strike() {
        int pinCount = 10;
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.addPintCount(pinCount);

        assertThat(normalFrame.isDone()).isTrue();
    }

    @Test
    @DisplayName("normalFrame 스페어 테스트")
    void normal_frame_is_done_when_spare() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.addPintCount(3);
        normalFrame.addPintCount(7);

        assertThat(normalFrame.isDone()).isTrue();
    }



    @Test
    void final_frame_is_done_when_not_strike_at_first_try() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.addPintCount(5);

        assertThat(finalFrame.isDone()).isFalse();
    }

    @Test
    void final_frame_is_done_when_strike_at_first_try() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.addPintCount(5);

        assertThat(finalFrame.isDone()).isFalse();
    }

    @Test
    void final_frame_is_done_when_strike_at_first_try() {
        FinalFrame finalFrame = new FinalFrame(10);
        finalFrame.addPintCount(5);

        assertThat(finalFrame.isDone()).isFalse();
    }


}
}
