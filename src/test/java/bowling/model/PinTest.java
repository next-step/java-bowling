package bowling.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}