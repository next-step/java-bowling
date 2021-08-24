package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.exception.CannotBeBiggerThanMax;
import bowling.exception.CannotBeLessThanZero;
import org.junit.jupiter.api.Test;

public class PinsTest {
    @Test
    void 예외처리_0보다작을때() {
        assertThatThrownBy(() -> {
            Pins.pitching(-1);
        }).isInstanceOf(CannotBeLessThanZero.class);
    }

    @Test
    void 예외처리_10보다클때() {
        assertThatThrownBy(() -> {
            Pins.pitching(11);
        }).isInstanceOf(CannotBeBiggerThanMax.class);
    }

    @Test
    void 스트라이크() {
        Pins pins = Pins.pitching(10);
        assertThat(pins.isStrike()).isTrue();
    }

    @Test
    void 기회합산핀() {
        Pins pins = Pins.pitching(4);
        assertThat(pins.totalPins(Pins.pitching(4))).isEqualTo(8);
    }


    @Test
    void 스페어() {
        Pins pins = Pins.pitching(7);
        assertThat(pins.isSpare(Pins.pitching(3))).isTrue();
    }

    @Test
    void 스페어_예외() {
        Pins pins = Pins.pitching(7);
        assertThatThrownBy(() -> {
            pins.isSpare(Pins.pitching(4));
        }).isInstanceOf(CannotBeBiggerThanMax.class);
    }

    @Test
    void 일반점수() {
        Pins pins = Pins.pitching(4);
        assertThat(pins.isMiss(Pins.pitching(4))).isTrue();
    }

    @Test
    void 일반점수_화면() {
        Pins pins = Pins.pitching(4);
        assertThat(pins.display()).isEqualTo("4  |");
    }

    @Test
    void 스트라이크_화면() {
        Pins pins = Pins.pitching(10);
        assertThat(pins.display()).isEqualTo("  X  |");
    }

    @Test
    void 스페어_화면() {
        Pins pins = Pins.pitching(7);
        assertThat(pins.display(Pins.pitching(3))).isEqualTo("7 | / |");
    }

    @Test
    void 스페어_처리못한_화면() {
        Pins pins = Pins.pitching(7);
        assertThat(pins.display(Pins.pitching(2))).isEqualTo("7 | 2 |");
    }

    @Test
    void 거터_화면() {
        Pins pins = Pins.pitching(0);
        assertThat(pins.display(Pins.pitching(0))).isEqualTo("- | - |");
    }

    @Test
    void 못맞춘후_스페어() {
        Pins pins = Pins.pitching(0);
        assertThat(pins.display(Pins.pitching(10))).isEqualTo("- | / |");
    }

}
