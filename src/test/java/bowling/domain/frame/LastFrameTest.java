package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
import bowling.domain.state.Miss;
import bowling.domain.state.Start;

class LastFrameTest {

	@DisplayName("프레임 종료")
	@Test
	void isFinish() {
		// given
		final LastFrame lastFrame = LastFrame.of();
		lastFrame.hitPins(Pins.of(1));
		lastFrame.hitPins(Pins.of(2));

		// when
		final boolean result = lastFrame.isFinish();

		// then
		assertThat(result).isTrue();
	}

	@DisplayName("hitPins - miss")
	@Test
	void hitPins_miss() {
		// given
		final Pins pins = Pins.of(3);
		final LastFrame frame = LastFrame.of();
		assertThat(frame.getPitchStates().getStart()).isInstanceOf(Start.class);

		// when
		frame.hitPins(pins);
		frame.hitPins(pins);

		// then
		assertThat(frame.getPitchStates().getStart()).isInstanceOf(Miss.class);
		assertThat(frame.isFinish()).isTrue();
	}

	private static Stream<Arguments> score() {
		return Stream.of(
			Arguments.of(Collections.emptyList(), ComputableScore.ofZero()),
			Arguments.of(Collections.singletonList(Pins.of(10)), ProgressScore.ofStrike()),
			Arguments.of(Collections.singletonList(Pins.of(1)), ComputableScore.ofZero()),
			Arguments.of(Arrays.asList(Pins.of(1), Pins.of(9)), ProgressScore.ofSpare()),
			Arguments.of(Arrays.asList(Pins.of(9), Pins.of(1)), ProgressScore.ofSpare()),
			Arguments.of(Arrays.asList(Pins.of(1), Pins.of(5)), ComputableScore.of(6))
		);
	}

	@DisplayName("점수 계산")
	@ParameterizedTest
	@MethodSource
	void score(final List<Pins> pins, final Score expectedScore) {
		// given
		final LastFrame lastFrame = LastFrame.of();

		// when
		pins.forEach(lastFrame::hitPins);
		final Score score = lastFrame.getScore();

		// then
		assertThat(score).isEqualTo(expectedScore);
	}
}
