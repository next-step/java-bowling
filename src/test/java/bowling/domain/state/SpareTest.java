package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.Pins;

class SpareTest {
	private static final Pins FIVE = Pins.create(5);

	private State spare;

	@BeforeEach
	void beforeEach() {
		spare = Spare.create(FIVE, FIVE);
	}

	@DisplayName("볼링 실행 검증")
	@Test
	void bowl() {
		// when then
		assertThatExceptionOfType(UnsupportedOperationException.class)
			.isThrownBy(() -> spare.bowl(FIVE));
	}

	@DisplayName("종료 상태 검증")
	@Test
	void isEnd() {
		// when
		boolean result = spare.isEnd();

		//then
		assertThat(result).isTrue();
	}

	@DisplayName("현재 상태의 symbol 검증")
	@Test
	void symbol() {
		// when
		String symbol = spare.symbol();

		// then
		assertThat(symbol).isEqualTo("5|/");
	}
}
