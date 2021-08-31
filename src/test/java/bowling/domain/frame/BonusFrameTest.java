package bowling.domain.frame;

import bowling.domain.pins.Pins;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BonusFrameTest {

    @DisplayName("마지막 프레임에 스트라이크를 치면 두번 더 투구할 수 있다.")
    @Test
    void final_frame_strike() {
        Frame frame = BonusFrame.of(Strike.of());
        frame.bowl(Pins.of(10));
        frame.bowl(Pins.of(10));

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.getState()).isEqualTo("XXX");
    }

    @DisplayName("마지막 프레임에 스페어를 치면 한번 더 투구 할 수 있다.")
    @Test
    void final_frame_spare() {
        Frame frame = BonusFrame.of(Spare.of(Pins.of(3), Pins.of(7)));
        frame.bowl(Pins.of(5));

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.getState()).isEqualTo("3|/5");
    }
}