package bowling.domain.pin;

import bowling.domain.frame.NormalFrame;
import bowling.domain.score.ScoreType2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class NormalFramePinsTest {

    @DisplayName("미스 상태인지 테스트")
    @Test
    void miss() {
        Pins2 pins = new NormalFramePins();
        pins.down(3);
        pins.down(4);

        assertThat(pins.getScoreType()).isEqualTo(ScoreType2.MISS);
    }

    @DisplayName("두번 이상 핀 테스트")
    @Test
    void normalFramePin_third() {
        Pins2 pins = new NormalFramePins();
        pins.down(3);
        pins.down(4);

        assertThatThrownBy(() -> {
            pins.down(4);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스트라이크 이후 핀 테스트")
    @Test
    void normalFrame_strike_third() {
        Pins2 pins = new NormalFramePins();
        pins.down(10);

        assertThatThrownBy(() -> {
            pins.down(4);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("핀 10개 이상 발생시 에러")
    @Test
    void normalFrame_pin() {
        Pins2 pins = new NormalFramePins();

        assertThatThrownBy(() -> {
            pins.down(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("핀 합계 유효성 테스트")
    @Test
    void normalFrame_sum_pin() {
        Pins2 pins = new NormalFramePins();
        pins.down(5);
        assertThatThrownBy(() -> {
            pins.down(6);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
