package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import bowling.exception.PinsRangeException;

class PinsTest {
	@DisplayName("Pins 의 가능 범위가 아닐 경우 예외 발생")
	@ParameterizedTest
	@ValueSource(ints = {Pins.MIN_OF_PINS - 1, Pins.MAX_OF_PINS + 1})
	void invalid(int invalidPins) {
		// when then
		assertThatExceptionOfType(PinsRangeException.class)
			.isThrownBy(() -> Pins.create(invalidPins));
	}

	@DisplayName("Pins 의 value 값 검증")
	@Test
	void getValue() {
		// given
		int expected = 10;
		Pins pins = Pins.create(expected);

		// when
		int result = pins.getValue();

		// then
		assertThat(result).isEqualTo(expected);
	}

	@DisplayName("Pins 의 strike 여부 검증")
	@Test
	void isStrike() {
		// given
		Pins pins = Pins.create(10);

		// when
		boolean result = pins.isStrike();

		// then
		assertThat(result).isTrue();
	}

	@DisplayName("Pins 의 spare 여부 검증")
	@Test
	void isSpare() {
		// given
		Pins first = Pins.create(7);
		Pins second = Pins.create(3);

		// when
		boolean result = first.isSpare(second);

		// then
		assertThat(result).isTrue();
	}

	@DisplayName("Pins 의 gutter 여부 검증")
	@Test
	void isGutter() {
		// given
		Pins pins = Pins.create(0);

		// when
		boolean result = pins.isGutter();

		// then
		assertThat(result).isTrue();
	}
}
