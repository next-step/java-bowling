package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.Pins;

class FirstBowlTest {
	@DisplayName("볼링 실행 검증")
	@ParameterizedTest
	@MethodSource("providePinsAndChangedState")
	void bowl(Pins first, Pins second, Class<State> expectedChangedState) {
		// given
		State firstBowl = FirstBowl.create(first);

		// when
		State result = firstBowl.bowl(second);

		// then
		assertThat(result).isInstanceOf(expectedChangedState);
	}

	@DisplayName("종료 상태 검증")
	@Test
	void isEnd() {
		// given
		Pins pins = Pins.create(5);
		State firstBowl = FirstBowl.create(pins);

		// when
		boolean result = firstBowl.isEnd();

		// then
		assertThat(result).isFalse();
	}

	@DisplayName("현재 상태의 symbol 검증")
	@Test
	void symbol() {
		// given
		Pins pins = Pins.create(5);
		State firstBowl = FirstBowl.create(pins);

		// when
		String symbol = firstBowl.symbol();

		// then
		assertThat(symbol).isEqualTo(String.valueOf(5));
	}

	private static Stream<Arguments> providePinsAndChangedState() {
		return Stream.of(
			arguments(Pins.create(5), Pins.create(5), Spare.class),
			arguments(Pins.create(5), Pins.create(4), Miss.class)
		);
	}
}
