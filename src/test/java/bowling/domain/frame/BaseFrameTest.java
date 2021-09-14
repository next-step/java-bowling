package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

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

@DisplayName("일반 프레임")
class BaseFrameTest {

	@DisplayName("프레임 종료")
	@Test
	void isFinish() {
		// given

		// when
		final boolean result = BaseFrame.of().isFinish();

		// then
		assertThat(result).isFalse();
	}

	static Stream<Arguments> addFrame() {
		return Stream.of(
			Arguments.of(Pins.of(10), 2),
			Arguments.of(Pins.of(9), 1)
		);
	}

	@DisplayName("점수 계산 - strike")
	@Test
	void getScore_strike() {
		// given
		final BaseFrame frame = BaseFrame.of();
		frame.hitPins(Pins.of(10));

		// when
		final Score score = frame.getScore();

		// then
		assertThat(score).isEqualTo(ProgressScore.ofStrike());
	}

	static Stream<Arguments> getScore() {
		return Stream.of(
			Arguments.of(Pins.of(1), Pins.of(9), ProgressScore.of(10, 1)),
			Arguments.of(Pins.of(1), Pins.of(2), ComputableScore.of(Pins.of(3).score()))
		);
	}

	@DisplayName("점수 계산 - spare, miss")
	@ParameterizedTest
	@MethodSource
	void getScore(final Pins hitPins1, final Pins hitPins2, final Score expected) {
		// given
		final BaseFrame frame = BaseFrame.of();
		frame.hitPins(hitPins1);
		frame.hitPins(hitPins2);

		// when
		final Score score = frame.getScore();

		// then
		assertThat(score).isEqualTo(expected);
	}
}
