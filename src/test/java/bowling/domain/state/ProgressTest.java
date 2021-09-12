package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
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

class ProgressTest {

	@DisplayName("생성")
	@ParameterizedTest
	@ValueSource(ints = {
		1, 8, 10
	})
	void create(final int pinCount) {
		// given

		// when
		final Progress progress = Progress.of(Pins.of(pinCount));

		// then
		assertThat(progress.isProgress()).isTrue();
	}

	static Stream<Arguments> hitPins() {
		return Stream.of(
			Arguments.of(Progress.of(Pins.of(2)), Pins.of(8), Spare.class),
			Arguments.of(Progress.of(Pins.of(2)), Pins.of(4), Miss.class)
		);
	}

	@DisplayName("hitPins")
	@ParameterizedTest
	@MethodSource
	void hitPins(final PitchState progress, final Pins hitPins, final Class<?> expected) {
		// given

		// when
		final PitchState result = progress.hitPins(hitPins);

		// then
		assertThat(result).isInstanceOf(expected);
	}

	@DisplayName("쓰러진 핀 가져오기")
	@Test
	void getHitPins() {
		// given
		final Pins pins = Pins.of(3);
		final Progress progress = Progress.of(pins);

		// when
		final List<Pins> hitPins = progress.getHitPins();

		// then
		assertThat(hitPins).contains(pins);
	}

	private static Stream<Arguments> addScore() {
		return Stream.of(
			Arguments.of(Progress.of(Pins.of(1)), ProgressScore.ofStrike(), ProgressScore.of(11, 1)),
			Arguments.of(Progress.of(Pins.of(1)), ProgressScore.ofSpare(), ComputableScore.of(11)),
			Arguments.of(Progress.of(Pins.of(2)), ProgressScore.of(2, 1), ComputableScore.of(4)),
			Arguments.of(Progress.of(Pins.of(2)), ProgressScore.of(4, 2), ProgressScore.of(6, 1))
		);
	}

	@DisplayName("점수 더하기")
	@ParameterizedTest
	@MethodSource
	void addScore(final Progress progress, final Score score, final Score expected) {
		// given

		// when
		final Score result = progress.addScore(score);

		// then
		assertThat(result).isEqualTo(expected);
	}
}
