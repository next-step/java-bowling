package bowling.domain.frame;

import bowling.domain.pins.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    FinalFrame frame;

    @BeforeEach
    void setup() {
        frame = FinalFrame.of();
    }

    @DisplayName("10 프레임의 2번째 투구가 스트라이크이면 한번을 더 투구 할 수 있다.")
    @Test
    void final_frame_strike() {
        frame.bowl(Pins.of(10));
        frame.bowl(Pins.of(10));

        assertThat(frame.isFinish()).isFalse();
    }

    @DisplayName("10 프레임의 2번째 투구가 스페어이면 한번을 더 투구 할 수 있다.")
    @Test
    void final_frame_spare() {
        frame.bowl(Pins.of(3));
        frame.bowl(Pins.of(7));

        assertThat(frame.isFinish()).isFalse();
    }

    @DisplayName("10 프레임의 2번째 투구가 미스이면 한 번 더 투구 할 수 없다.")
    @Test
    void final_frame_miss() {
        frame.bowl(Pins.of(3));
        frame.bowl(Pins.of(4));

        assertThat(frame.isFinish()).isTrue();
    }

    @DisplayName("10 프레임의 2번째 투구가 거터이면 한 번 더 투구 할 수 없다.")
    @Test
    void final_frame_gutter() {
        frame.bowl(Pins.of(0));
        frame.bowl(Pins.of(0));

        assertThat(frame.isFinish()).isTrue();
    }

    @DisplayName("10 프레임에 3번까지 투구하면 더이상 투구 할 수 없다.")
    @Test
    void final_frame_three_pitch() {
        frame.bowl(Pins.of(10));
        frame.bowl(Pins.of(10));
        frame.bowl(Pins.of(10));

        assertThat(frame.isFinish()).isTrue();
    }
}