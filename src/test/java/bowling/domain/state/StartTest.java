package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.common.Pins;

class StartTest {

	@DisplayName("생성")
	@Test
	void create() {
		// given

		// when
		final Start start = Start.of();

		// then
		assertThat(start.isStart()).isTrue();
		assertThat(start.isProgress()).isTrue();
	}

	static Stream<Arguments> hitPins() {
		return Stream.of(
			Arguments.of(Pins.of(10), Strike.class),
			Arguments.of(Pins.of(9), Progress.class)
		);
	}

	@DisplayName("hitPins")
	@ParameterizedTest
	@MethodSource
	void hitPins(final Pins hitPins, final Class<?> expected) {
		// given
		final Start start = Start.of();

		// when
		final PitchState result = start.hitPins(hitPins);

		// then
		assertThat(result).isInstanceOf(expected);
	}

	@DisplayName("핀 가져오기")
	@Test
	void getHitPins() {
		// given
		final Start start = Start.of();

		// when
		final List<Pins> hitPins = start.getHitPins();

		// then
		assertThat(hitPins).isEmpty();
	}
}
