package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.domain.common.Score;
import bowling.domain.exception.InvalidMethodCallException;
import bowling.domain.frame.exception.InvalidProgressFrameException;

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

	@DisplayName("투구 - miss")
	@ParameterizedTest
	@CsvSource({
		"5,2"
	})
	void pitch_miss(final int first, final int second) {
		// given

		// when
		final Frame frame = FinalFrame.of()
			.pitch(first)
			.pitch(second);

		// then
		assertThat(frame.possiblePitch()).isFalse();
	}

	@DisplayName("투구 - strike")
	@ParameterizedTest
	@CsvSource({
		"10,5,2"
	})
	void pitch_strike(final int first, final int second, final int bonus) {
		// given

		// when
		final Frame frame = FinalFrame.of()
			.pitch(first)
			.pitch(second)
			.pitch(bonus);

		// then
		assertThat(frame.possiblePitch()).isFalse();
	}

	@DisplayName("투구 - spare")
	@ParameterizedTest
	@CsvSource({
		"5,5,2"
	})
	void pitch_spare(final int first, final int second, final int bonus) {
		// given

		// when
		final Frame frame = FinalFrame.of()
			.pitch(first)
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

	@DisplayName("점수 계산 - miss")
	@Test
	void score_miss() {
		// given

		// when
		final Frame frame = FinalFrame.of()
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

		// when
		final Frame frame = FinalFrame.of()
			.pitch(7)
			.pitch(3)
			.pitch(7);

		// then
		final Frames frames = new Frames(Collections.singletonList(frame));
		assertThat(frame.caculateScore(frames))
			.isEqualTo(new Score(17, 0));
	}

	@DisplayName("점수 계산 - strike")
	@Test
	void score_strike() {
		// given

		// when
		final Frame frame = FinalFrame.of()
			.pitch(10)
			.pitch(10)
			.pitch(10);

		// then
		final Frames frames = new Frames(Collections.singletonList(frame));
		assertThat(frame.caculateScore(frames))
			.isEqualTo(new Score(30, 0));
	}
}
