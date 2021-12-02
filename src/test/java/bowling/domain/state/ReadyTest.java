package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.Pins;

class ReadyTest {
	private State ready;

	@BeforeEach
	void beforeEach() {
		ready = Ready.create();
	}

	@DisplayName("볼링 실행 검증")
	@ParameterizedTest
	@MethodSource("providePinsAndChangedState")
	void bowl(Pins pins, Class<State> expectedChangedState) {
		// when
		State result = ready.bowl(pins);

		// then
		assertThat(result).isInstanceOf(expectedChangedState);
	}

	@DisplayName("종료 상태 검증")
	@Test
	void isEnd() {
		// when
		boolean result = ready.isEnd();

		//then
		assertThat(result).isFalse();
	}

	@DisplayName("현재 상태의 symbol 검증")
	@Test
	void symbol() {
		// when
		String symbol = ready.symbol();

		// then
		assertThat(symbol).isBlank();
	}

	private static Stream<Arguments> providePinsAndChangedState() {
		return Stream.of(
			arguments(Pins.create(0), FirstBowl.class),
			arguments(Pins.create(1), FirstBowl.class),
			arguments(Pins.create(2), FirstBowl.class),
			arguments(Pins.create(3), FirstBowl.class),
			arguments(Pins.create(4), FirstBowl.class),
			arguments(Pins.create(5), FirstBowl.class),
			arguments(Pins.create(6), FirstBowl.class),
			arguments(Pins.create(7), FirstBowl.class),
			arguments(Pins.create(8), FirstBowl.class),
			arguments(Pins.create(9), FirstBowl.class),
			arguments(Pins.create(10), Strike.class)
		);
	}
}
