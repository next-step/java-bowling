package bowling.domain.pin;

import bowling.exception.BowlCountOverThanPinsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PinsTest {

    @DisplayName("10개의 핀을 가진 Pins 객체를 생성할 수 있다.")
    @Test
    void create() {
        Pins expect = Pins.of();

        Pins actual = Pins.of();

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("핀을 넘어트릴 수 있다.")
    @Test
    void getStandingPins() {
        Pins pins = Pins.of();
        BowlCount bowlCount = new BowlCount(3);

        final Pins actual = pins.knockOver(bowlCount);

        assertThat(actual.knockOverCount()).isEqualTo(3);
    }

    @DisplayName("투구수가 핀의 갯수보다 많을경우 예외를 발생한다.")
    @Test
    void checkKnockOver() {
        Pins pins = Pins.of();
        BowlCount bowlCount = new BowlCount(6);
        final Pins standingPins = pins.knockOver(bowlCount);

        assertThatExceptionOfType(BowlCountOverThanPinsException.class).isThrownBy(
                () -> standingPins.knockOver(bowlCount)
        );
    }
}