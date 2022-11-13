package bowling.domain.player;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import bowling.exception.BadRequestException;

class PlayerTest {

	@Nested
	class player {

		@Test
		@DisplayName("플레이어의 이름이 3글자이면 예외를 던지지 않는다.")
		void success() {
			assertThatNoException().isThrownBy(() -> new Player("123"));
		}

		@ParameterizedTest
		@ValueSource(strings = {"1", "12", "1234"})
		@DisplayName("플레이어의 이름이 3글자가 아니면 예외를 던진다.")
		void fail(String name) {
			assertThatThrownBy(() -> new Player(name))
				.isInstanceOf(BadRequestException.class);
		}
	}
}