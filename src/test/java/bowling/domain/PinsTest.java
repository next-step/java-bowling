package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import bowling.exception.PinsFirstBawlValidException;
import bowling.exception.PinsRangeException;
import bowling.exception.PinsSpareValidException;

class PinsTest {
	@DisplayName("Pins 의 가능 범위가 아닐 경우 예외 발생")
	@ParameterizedTest
	@ValueSource(ints = {Pins.MIN_OF_PINS - 1, Pins.MAX_OF_PINS + 1})
	void invalid(int invalidPins) {
		// when then
		assertThatExceptionOfType(PinsRangeException.class)
			.isThrownBy(() -> Pins.create(invalidPins));
	}

	@DisplayName("FirstBawl 검증, 두 Pins 의 합이 10을 넘을 경우 예외 발생")
	@Test
	void validateFirstBawl() {
		// given
		Pins first = Pins.create(5);
		Pins second = Pins.create(10);

		// when then
		assertThatExceptionOfType(PinsFirstBawlValidException.class)
			.isThrownBy(() -> Pins.validateFirstBawl(first, second));
	}

	@DisplayName("Spare 검증, 두 Pins 의 합이 10이 아닐 경우 예외 발생")
	@Test
	void validateSpare() {
		// given
		Pins first = Pins.create(5);
		Pins second = Pins.create(10);

		// when then
		assertThatExceptionOfType(PinsSpareValidException.class)
			.isThrownBy(() -> Pins.validateSpare(first, second));
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

	@DisplayName("Pins 를 스코어로 변환 검증")
	@Test
	void toScore() {
		// given
		Pins pins = Pins.create(5);

		// when
		Score score = pins.toScore();

		// then
		assertThat(score.getScore()).isEqualTo(5);
	}

	@DisplayName("Pins 를 스코어로 변환시 다른 핀과 점수 합산 검증")
	@Test
	void toScoreOtherPins() {
		// given
		Pins pins = Pins.create(5);
		Pins otherPins = Pins.create(10);

		// when
		Score score = pins.toScore(otherPins);

		// then
		assertThat(score.getScore()).isEqualTo(15);
	}
}
