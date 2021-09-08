package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.common.Pins;
import bowling.domain.score.ComputableScore;
import bowling.domain.score.ProgressScore;
import bowling.domain.score.Score;
import bowling.domain.state.exception.InvalidCreateStateException;
import bowling.domain.state.exception.InvalidProgressPitchException;

@DisplayName("투구 상태 - miss")
class MissTest {

	@DisplayName("생성")
	@ParameterizedTest
	@CsvSource({
		"1,2",
		"4,5",
	})
	void create(final int pinCount1, final int pinCount2) {
		// given
		final Pins pins1 = Pins.of(pinCount1);
		final Pins pins2 = Pins.of(pinCount2);

		// when
		final PitchState pitchState = Miss.of(pins1, pins2);

		// then
		assertThat(pitchState.isMiss()).isTrue();
		assertThat(pitchState.isFinish()).isTrue();
		assertThat(pitchState.score()).isInstanceOf(ComputableScore.class);
	}

	@DisplayName("생성 - 유효하지 않은 핀 개수")
	@ParameterizedTest
	@CsvSource({
		"1,9",
		"0,10",
	})
	void create_invalid(final int pinCount1, final int pinCount2) {
		// given
		final Pins pins1 = Pins.of(pinCount1);
		final Pins pins2 = Pins.of(pinCount2);

		// when
		assertThrows(InvalidCreateStateException.class, () -> Miss.of(pins1, pins2));

		// then

	}

	@DisplayName("hitPins - 불가능")
	@Test
	void hitPins_invalid() {
		// given
		final Miss miss = Miss.of(Pins.of(1), Pins.of(8));
		final Pins pins = Pins.of(9);

		// when
		assertThrows(InvalidProgressPitchException.class, () -> miss.hitPins(pins));

		// then

	}

	@DisplayName("쓰러진 핀 개수 가져오기")
	@ParameterizedTest
	@CsvSource({
		"1, 8",
	})
	void getHitPins(final int pinCount1, final int pinCount2) {
		// given
		final Miss miss = Miss.of(Pins.of(pinCount1), Pins.of(pinCount2));

		// when
		final List<Integer> hitPins = miss.getHitPins();

		// then
		assertThat(hitPins.get(0)).isEqualTo(pinCount1);
		assertThat(hitPins.get(1)).isEqualTo(pinCount2);
	}

	static Stream<Arguments> addScore() {
		return Stream.of(
			Arguments.of(Miss.of(Pins.of(1), Pins.of(8)), ProgressScore.ofStrike(), ComputableScore.of(19)),
			Arguments.of(Miss.of(Pins.of(1), Pins.of(8)), ProgressScore.ofSpare(), ComputableScore.of(11))
		);
	}

	@DisplayName("점수 계산")
	@ParameterizedTest
	@MethodSource
	void addScore(final PitchState pitchState, final Score score, final Score expected) {
		// given

		// when
		final Score result = pitchState.addScore(score);

		// then
		assertThat(result).isEqualTo(expected);
	}
}
