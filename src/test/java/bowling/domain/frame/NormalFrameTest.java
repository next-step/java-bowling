package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.domain.frame.exception.InvalidProgressFrameException;
import bowling.domain.frame.exception.PitchesFullException;

@DisplayName("일반 프레임")
class NormalFrameTest {

	@DisplayName("생성")
	@Test
	void create() {
		// given

		// when
		final Frame frame = NormalFrame.of();

		// then
		assertThat(frame.possiblePitch()).isTrue();
	}

	@DisplayName("투구 - 최대 개수 초과 (2개)")
	@Test
	void pitch_excessMaxCount_2() {
		// given
		final Frame frame = NormalFrame.of()
			.pitch(5)
			.pitch(5);

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
		Frame frame = NormalFrame.of();

		// when
		frame = frame
			.pitch(first)
			.pitch(second);

		// then
		assertThat(frame.possiblePitch()).isFalse();
	}

	@DisplayName("투구 - 스트라이크")
	@Test
	void pitch_strike() {
		// given
		Frame frame = NormalFrame.of();

		// when
		frame = frame.pitch(10);

		// then
		assertThat(frame.possiblePitch()).isFalse();
	}

	@DisplayName("다음 프레임")
	@Test
	void next() {
		// given

		// when
		final Frame next = NormalFrame.of().next();

		// then
		assertThat(next).isInstanceOf(NormalFrame.class);
	}

	@DisplayName("마지막 프레임")
	@Test
	void last() {
		// given

		// when
		final Frame last = NormalFrame.of().last();

		// then
		assertThat(last).isInstanceOf(FinalFrame.class);
	}
}
