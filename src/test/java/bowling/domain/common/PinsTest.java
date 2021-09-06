package bowling.domain.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.domain.exception.InvalidPinCountException;

@DisplayName("쓰러진 핀")
class PinsTest {

	@DisplayName("[실패] 생성 - 유효하지 않은 핀 개수")
	@ParameterizedTest
	@CsvSource({
		"-1", "11"
	})
	void create_invalidPinCount(final int pinCount) {
		// given

		// when
		Assertions.assertThrows(InvalidPinCountException.class, () -> Pins.of(pinCount));

		// then
	}
}
