package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import bowling.domain.common.Pins;
import bowling.domain.score.ComputableScore;
import bowling.domain.score.ProgressScore;
import bowling.domain.score.Score;
import bowling.domain.state.exception.InvalidProgressPitchException;

class SpareTest {

	@DisplayName("생성")
	@ParameterizedTest
	@ValueSource(ints = {
		1
	})
	void create(final int pinCount) {
		// given

		// when
		final Spare spare = Spare.of(Pins.of(pinCount));

		// then
		assertThat(spare.isFinish()).isTrue();
		assertThat(spare).isInstanceOf(Spare.class);
	}

	@DisplayName("hitPins - 불가능")
	@Test
	void hitPins_invalid() {
		// given
		final Spare spare = Spare.of(Pins.of(1));
		final Pins pins = Pins.of(9);

		// when
		assertThrows(InvalidProgressPitchException.class, () -> spare.hitPins(pins));

		// then

	}

	static Stream<Arguments> addScore() {
		return Stream.of(
			Arguments.of(Spare.of(Pins.of(1)), Strike.of().score(), ComputableScore.of(20)),
			Arguments.of(Spare.of(Pins.of(1)), Spare.of(Pins.of(1)).score(), ComputableScore.of(11)),
			Arguments.of(Spare.of(Pins.of(1)), ProgressScore.of(1, 1), ComputableScore.of(2)),
			Arguments.of(Spare.of(Pins.of(1)), ProgressScore.of(1, 2), ComputableScore.of(11))
		);
	}

	@DisplayName("점수 계산")
	@ParameterizedTest
	@MethodSource
	void addScore(final Spare spare, final Score addScore, final Score expected) {
		// given

		// when
		final Score result = spare.addScore(addScore);

		// then
		assertThat(result).isEqualTo(expected);
	}
}
