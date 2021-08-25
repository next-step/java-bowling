package bowling.domain.frame;

import bowling.domain.pins.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    Frame frame = FinalFrame.of();

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
}