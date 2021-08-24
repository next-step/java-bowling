package bowling.domain;

import static bowling.domain.PitchResult.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("마지막 프레임")
class LastFrameTest {

	static Stream<Arguments> isEnd() {
		return Stream.of(
			Arguments.of(new LastFrame(new Pitch(1)), false),
			Arguments.of(new LastFrame(new Pitch(10)), false),
			Arguments.of(new LastFrame(new Pitch(5), new Pitch(5)), false),
			Arguments.of(new LastFrame(new Pitch(5), new Pitch(4)), true),
			Arguments.of(new LastFrame(new Pitch(5), new Pitch(5), new Pitch(5)), true)
		);
	}

	@DisplayName("완료된 마지막 프레임")
	@ParameterizedTest
	@MethodSource("isEnd")
	void isEnd(final LastFrame lastFrame, final boolean expected) {
		// given

		// when
		assertThat(lastFrame.isEnd()).isEqualTo(expected);

		// then
	}

	static Stream<Arguments> second() {
		return Stream.of(
			Arguments.of(new LastFrame(new Pitch(1)), 9, SPARE),
			Arguments.of(new LastFrame(new Pitch(10)), 1, MISS),
			Arguments.of(new LastFrame(new Pitch(5)), 4, MISS),
			Arguments.of(new LastFrame(new Pitch(5)), 0, GUTTER)
		);
	}

	@DisplayName("마지막 프레임 - second")
	@ParameterizedTest
	@MethodSource("second")
	void pitch_second(final LastFrame lastFrame, final int pinCount, final PitchResult secondResult) {
		// given

		// when
		lastFrame.pitch(pinCount);

		// then
		assertThat(lastFrame.second.getPitchResult()).isEqualTo(secondResult);
	}

	static Stream<Arguments> bonus() {
		return Stream.of(
			Arguments.of(new LastFrame(new Pitch(1)), 9, 10, STRIKE),
			Arguments.of(new LastFrame(new Pitch(1)), 9, 0, GUTTER),
			Arguments.of(new LastFrame(new Pitch(10)), 1, 5, MISS),
			Arguments.of(new LastFrame(new Pitch(10)), 1, 9, SPARE)
		);
	}

	@DisplayName("마지막 프레임 - bonus")
	@ParameterizedTest
	@MethodSource("bonus")
	void pitch_bonus(final LastFrame lastFrame, final int secondPinCount, final int bonusPinCount,
		final PitchResult bonusResult) {
		// given

		// when
		lastFrame.pitch(secondPinCount);
		lastFrame.pitch(bonusPinCount);

		// then
		assertThat(lastFrame.getBonus().getPitchResult()).isEqualTo(bonusResult);
	}
}
