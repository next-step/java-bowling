package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.Pins;

class MissTest {
	private static final Pins FIVE = Pins.create(5);
	private static final Pins FOUR = Pins.create(4);
	private static final Pins ZERO = Pins.create(0);

	@DisplayName("볼링 실행 검증")
	@Test
	void bowl() {
		// given
		State miss = Miss.create(FIVE, FOUR);

		// when then
		assertThatExceptionOfType(UnsupportedOperationException.class)
			.isThrownBy(() -> miss.bowl(ZERO));
	}

	@DisplayName("종료 상태 검증")
	@Test
	void isEnd() {
		// given
		State miss = Miss.create(FIVE, FOUR);

		// when
		boolean result = miss.isEnd();

		//then
		assertThat(result).isTrue();
	}

	@DisplayName("현재 상태의 symbol 검증")
	@Test
	void symbol() {
		// given
		State miss = Miss.create(FIVE, FOUR);
		State missWithGutter = Miss.create(FIVE, ZERO);

		// when
		String symbol = miss.symbol();
		String symbolGutter = missWithGutter.symbol();

		//then
		assertAll(
			() -> assertThat(symbol).isEqualTo("5|4"),
			() -> assertThat(symbolGutter).isEqualTo("5|-")
		);
	}
}
