package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.domain.common.Score;
import bowling.domain.frame.exception.InvalidProgressFrameException;

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

	@DisplayName("투구 - miss")
	@ParameterizedTest
	@CsvSource({
		"5,2"
	})
	void pitch_miss(final int first, final int second) {
		// given
		Frame frame = NormalFrame.of();

		// when
		frame = frame
			.pitch(first)
			.pitch(second);

		// then
		assertThat(frame.possiblePitch()).isFalse();
	}

	@DisplayName("투구 - strike")
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

	@DisplayName("점수 계산 - miss")
	@Test
	void score_miss() {
		// given

		// when
		final Frame frame = NormalFrame.of()
			.pitch(7)
			.pitch(2);

		// then
		final Frames frames = new Frames(Collections.singletonList(frame));
		assertThat(frame.caculateScore(frames))
			.isEqualTo(new Score(9, 0));
	}

	@DisplayName("점수 계산 - spare")
	@Test
	void score_spare() {
		// given
		final Frame frame = NormalFrame.of()
			.pitch(7)
			.pitch(3);

		// when
		final Frame next = frame.next()
			.pitch(7);

		// then
		final Frames frames = new Frames(Arrays.asList(frame, next));
		assertThat(frame.caculateScore(frames))
			.isEqualTo(new Score(17, 0));
	}

	@DisplayName("점수 계산 - strike")
	@Test
	void score_strike() {
		// given
		final Frame frame = NormalFrame.of()
			.pitch(10);

		// when
		final Frame next1 = frame.next()
			.pitch(10);

		final Frame next2 = next1.next()
			.pitch(10);

		// then
		final Frames frames = new Frames(Arrays.asList(frame, next1, next2));
		assertThat(frame.caculateScore(frames))
			.isEqualTo(new Score(30, 0));
	}

	@DisplayName("점수 계산 - spare - 마지막 프레임")
	@Test
	void score_spare_lastFrame() {
		// given
		final Frame frame = NormalFrame.of()
			.pitch(7)
			.pitch(3);

		// when
		final Frame last = frame.last()
			.pitch(10);

		// then
		final Frames frames = new Frames(Arrays.asList(frame, last));
		assertThat(frame.caculateScore(frames))
			.isEqualTo(new Score(20, 0));
	}

	@DisplayName("점수 계산 - strike - 마지막 프레임")
	@Test
	void score_strike_lastFrame() {
		// given
		final Frame frame = NormalFrame.of()
			.pitch(10);

		// when
		final Frame last = frame.last()
			.pitch(10)
			.pitch(10);

		// then
		final Frames frames = new Frames(Arrays.asList(frame, last));
		assertThat(frame.caculateScore(frames))
			.isEqualTo(new Score(30, 0));
	}
}
