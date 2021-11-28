package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import bowling.execption.PlayerNameSizeException;

class PlayerTest {
	@DisplayName("플레이어 이름이 영어 3자일 경우 생성 검증")
	@ParameterizedTest
	@ValueSource(strings = {"abc", "ABC", "aBc"})
	void create(String name) {
		// when then
		assertThatCode(() -> Player.create(name))
			.doesNotThrowAnyException();
	}

	@DisplayName("플레이어 이름이 영어 3자가 아닐 경우 예외 발생")
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"a", "ab", "apple", "123", "가나다"})
	void createWithInvalidName(String invalidName) {
		// when then
		assertThatExceptionOfType(PlayerNameSizeException.class)
			.isThrownBy(() -> Player.create(invalidName));
	}
}
