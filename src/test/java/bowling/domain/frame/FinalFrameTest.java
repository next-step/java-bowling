package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.domain.exception.InvalidMethodCallException;
import bowling.domain.frame.exception.InvalidProgressFrameException;
import bowling.domain.frame.exception.PitchesFullException;

@DisplayName("마지막 프레임")
class FinalFrameTest {

	@DisplayName("생성")
	@Test
	void create() {
		// given

		// when
		final Frame frame = FinalFrame.of();

		// then
		assertThat(frame.possiblePitch()).isTrue();
	}

	@DisplayName("투구 - 최대 개수 초과 (3개)")
	@Test
	void pitch_excessMaxCount_3() {
		// given
		final Frame frame = FinalFrame.of()
			.pitch(10)
			.pitch(10)
			.pitch(10);

		// when
		assertThrows(InvalidProgressFrameException.class, () -> frame.pitch(10));

		// then

	}

	@DisplayName("투구 - 최대 개수 초과 (2개)")
	@Test
	void pitch_excessMaxCount_2() {
		// given
		final Frame frame = FinalFrame.of()
			.pitch(5)
			.pitch(4);

		// when
		assertThrows(InvalidProgressFrameException.class, () -> frame.pitch(5));

		// then

	}

	@DisplayName("투구 - 일반")
	@ParameterizedTest
	@CsvSource({
		"5,2"
	})
	void pitch_normal(final int first, final int second) {
		// given
		Frame frame = FinalFrame.of();

		// when
		frame = frame
			.pitch(first)
			.pitch(second);

		// then
		assertThat(frame.possiblePitch()).isFalse();
	}

	@DisplayName("투구 - 스트라이크")
	@ParameterizedTest
	@CsvSource({
		"10,5,2"
	})
	void pitch_strike(final int first, final int second, final int bonus) {
		// given
		Frame frame = FinalFrame.of();

		// when
		frame = frame.pitch(first)
			.pitch(second)
			.pitch(bonus);

		// then
		assertThat(frame.possiblePitch()).isFalse();
	}

	@DisplayName("투구 - 스페어")
	@ParameterizedTest
	@CsvSource({
		"5,5,2"
	})
	void pitch_spare(final int first, final int second, final int bonus) {
		// given
		Frame frame = FinalFrame.of();

		// when
		frame = frame.pitch(first)
			.pitch(second)
			.pitch(bonus);

		// then
		assertThat(frame.possiblePitch()).isFalse();
	}

	@DisplayName("다음 프레임 - 잘못된 호출")
	@Test
	void next_invalid() {
		// given
		final Frame frame = FinalFrame.of();

		// when
		assertThrows(InvalidMethodCallException.class, frame::next);

		// then

	}

	@DisplayName("마지막 프레임 - 잘못된 호출")
	@Test
	void last_invalid() {
		// given
		final Frame frame = FinalFrame.of();

		// when
		assertThrows(InvalidMethodCallException.class, frame::last);

		// then

	}
}
