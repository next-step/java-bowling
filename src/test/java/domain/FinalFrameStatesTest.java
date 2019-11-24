package domain;

import domain.frame_states.FrameStates;
import domain.frame_states.NormalFrameStates;
import domain.state.*;
import domain.states.BowlingPins;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * TDD로 리팩토링 시작
 * 나올 수 있는 TEST CASE
 * FinalFrameStates
 * - 초기 상태에서 10개를 하면 STRIKE, 두 번 더 할 수 있음
 * - N개, (10 - N) 개, (N != 0) 을 하면 IN_PROGRESS, SPARE 가 States에 존재하고 한 번더 할 수 있음
 * - 3개 6개 처럼 10개가 안되게 볼링핀을 쓰러뜨리면 IN_PROGRESS, MISS / 끝
 * - 0개, 0개 이면 IN_PROGRESS, GUTTER / 끝
 *
 * 제일 끝에는 무조건 MISS로 생각한다
 */
@SuppressWarnings("NonAsciiCharacters")
class FinalFrameStatesTest {

	@Test
	void 처음에_10개를_치면_스트라이크이고_두_번_더할수_있다() {
		// given
		FrameStates frameStates = FinalFrameStates.newInstance();

		// when
		frameStates.roll(BowlingPins.of(10));
		frameStates.roll(BowlingPins.of(4));
		frameStates.roll(BowlingPins.of(3));

		// then
		assertThat(frameStates).isEqualTo(FinalFrameStates.of(3,
				Strike.of(10), InProgress.of(4), Miss.of(3)));
	}

	@Test
	void 처음에_4개_그다음_6개를_치면_스페어이고_한_번_더할수_있다() {
		// given
		FrameStates frameStates = FinalFrameStates.newInstance();

		// when
		frameStates.roll(BowlingPins.of(4));
		frameStates.roll(BowlingPins.of(6));
		frameStates.roll(BowlingPins.of(5));

		// then
		assertThat(frameStates).isEqualTo(FinalFrameStates.of(5,
				InProgress.of(4), Spare.of(6), Miss.of(5)));
	}

	@Test
	void 처음에_5개_그다음_4개를_치면_미스이다() {
		// given
		FrameStates frameStates = FinalFrameStates.newInstance();

		// when
		frameStates.roll(BowlingPins.of(5));
		frameStates.roll(BowlingPins.of(4));

		// then
		assertThat(frameStates).isEqualTo(FinalFrameStates.of(1, InProgress.of(5), Miss.of(4)));
	}

	@Test
	void 처음에_0개_그다음_0개를_치면_거터이다() {
		// given
		FrameStates frameStates = FinalFrameStates.newInstance();

		// when
		frameStates.roll(BowlingPins.of(0));
		frameStates.roll(BowlingPins.of(0));

		// then
		assertThat(frameStates).isEqualTo(FinalFrameStates.of(10, InProgress.of(0), Gutter.getInstance()));
	}

	@Test
	void 스트라이크나_스페어가_아니면_두_번_친_이후에_더_공을_칠_수_없다() {
		// given
		FrameStates frameStates = FinalFrameStates.newInstance();
		frameStates.roll(BowlingPins.of(4));
		frameStates.roll(BowlingPins.of(5));

		// when & then
		assertThatThrownBy(() -> {
			frameStates.roll(BowlingPins.of(1));
		})
		.isInstanceOf(IllegalStateException.class);
	}

	@Test
	void 존재하는_핀보다_많은_핀을_칠_수_없다() {
		// given
		FrameStates frameStates = FinalFrameStates.newInstance();
		frameStates.roll(BowlingPins.of(4));

		// when & then
		assertThatThrownBy(() -> {
			frameStates.roll(BowlingPins.of(10));
		})
		.isInstanceOf(IllegalArgumentException.class);
	}

}
