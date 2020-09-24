package bowling.step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PinTest {

	@DisplayName(value = "올바르지 않은 핀 개수 입력 시 에러 발생")
	@ParameterizedTest
	@ValueSource(ints = {Integer.MIN_VALUE, -5, 11, 100, Integer.MAX_VALUE})
	void validPinCount(int pinCount) {
		assertThatIllegalArgumentException()
				.isThrownBy(() -> Pin.bowlPin(pinCount))
				.withMessage("쓰러트릴 수 있는 핀은 최소 0개, 최대 10개 입니다.");
	}

	@DisplayName(value = "스페어 여부 확인")
	@Test
	void spareOrNot() {
		Pin pin = Pin.bowlPin(5);
		assertThat(pin.isSpare(new Pin(5))).isTrue();
		assertThat(pin.isSpare(new Pin(3))).isFalse();
	}
}
