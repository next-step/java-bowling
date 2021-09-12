package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.common.Pins;
import bowling.domain.score.ComputableScore;
import bowling.domain.score.ProgressScore;
import bowling.domain.score.Score;
import bowling.domain.state.exception.InvalidProgressPitchException;

class StrikeTest {

	@DisplayName("생성")
	@Test
	void create() {
		// given

		// when
		final Strike strike = Strike.of();

		// then
		assertThat(strike.isFinish()).isTrue();
		assertThat(strike.isAllHit()).isTrue();
	}

	@DisplayName("hitPins - 불가능")
	@Test
	void hitPins_invalid() {
		// given
		final Strike strike = Strike.of();
		final Pins pins = Pins.of(9);

		// when
		assertThrows(InvalidProgressPitchException.class, () -> strike.hitPins(pins));

		// then

	}

	@DisplayName("쓰러진 핀 가져오기")
	@Test
	void getHitPins() {
		// given
		final Strike strike = Strike.of();

		// when
		final List<Pins> hitPins = strike.getHitPins();

		// then
		assertThat(hitPins).contains(Pins.of(10));
	}

	static Stream<Arguments> addScore() {
		return Stream.of(
			Arguments.of(Strike.of().score(), ProgressScore.of(20, 1)),
			Arguments.of(Spare.of(Pins.of(1)).score(), ComputableScore.of(20)),
			Arguments.of(ProgressScore.of(3, 1), ComputableScore.of(13)),
			Arguments.of(ProgressScore.of(1, 2), ProgressScore.of(11, 1))
		);
	}

	@DisplayName("점수 계산")
	@ParameterizedTest
	@MethodSource
	void addScore(final Score addScore, final Score expected) {
		// given
		final Strike strike = Strike.of();

		// when
		final Score result = strike.addScore(addScore);

		// then
		assertThat(result).isEqualTo(expected);
	}
}
