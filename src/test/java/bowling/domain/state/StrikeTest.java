package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.Pins;

class StrikeTest {
	private State strike;

	@BeforeEach
	void beforeEach() {
		strike = Strike.create();
	}

	@DisplayName("볼링 실행시 예외 발생")
	@Test
	void bowl() {
		// given
		Pins pins = Pins.create(5);

		// when then
		assertThatExceptionOfType(UnsupportedOperationException.class)
			.isThrownBy(() -> strike.bowl(pins));
	}

	@DisplayName("종료 상태 검증")
	@Test
	void isEnd() {
		// when
		boolean result = strike.isEnd();

		//then
		assertThat(result).isTrue();
	}

	@DisplayName("현재 상태의 symbol 검증")
	@Test
	void symbol() {
		// when
		String symbol = strike.symbol();

		// then
		assertThat(symbol).isEqualTo("X");
	}
}
