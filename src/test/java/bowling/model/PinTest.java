package bowling.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PinTest {

	@Test
	@DisplayName("핀을 생성하면 핀이 생성된다.")
	public void createPin() {
		Pin pin = new Pin(5);

		Assertions.assertAll(
			() -> assertThat(pin).isEqualTo(new Pin(5)),
			() -> assertThat(pin.getPin()).isEqualTo(5)
		);
	}

	@Test
	@DisplayName("핀은 음수를 입력하면 예외가 발생된다.")
	public void checkPositive() {
		assertThatThrownBy(() -> new Pin(-1))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("핀은 10을 넘으면 예외가 발생된다.")
	public void checkMaxPinOver() {
		assertThatThrownBy(() -> new Pin(11))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@DisplayName("핀의 수가 10개면 true 이다")
	@CsvSource(value = {"10,true", "9,false"})
	public void isMaxPin(int firstPin, boolean maxPin) {
		Pin pin = new Pin(firstPin);

		assertThat(pin.isMaxPin()).isEqualTo(maxPin);
	}

	@ParameterizedTest
	@DisplayName("핀의 수와 입력받은 수의 합이 10개면 true 이다")
	@CsvSource(value = {"2,8,true", "4,6,true", "2,7,false", "3,6,false"})
	public void isMaxPinBySecondPin(int firstPin, int secondPin, boolean maxPin) {
		Pin pin = new Pin(firstPin);

		assertThat(pin.add(new Pin(secondPin)).isMaxPin()).isEqualTo(maxPin);
	}

	@ParameterizedTest
	@DisplayName("핀의 수와 합이 10이상이면 true 이다.")
	@CsvSource(value = {"2,9,true", "4,5,false"})
	public void isOverPin(int firstPin, int secondPin, boolean maxPin) {
		Pin pin = new Pin(firstPin);

		assertThat(pin.isOverPin(new Pin(secondPin))).isEqualTo(maxPin);
	}

	@Test
	@DisplayName("핀의 수가 0이면 true 이다.")
	public void isMinPin() {
		Pin pin = new Pin(0);

		assertThat(pin.isMinPin()).isTrue();
	}

}