package domain;

import domain.state.InProgress;
import domain.state.Miss;
import domain.state.Spare;
import domain.state.Strike;
import domain.states.BowlingPins;
import domain.states.NormalStates;
import domain.states.States;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * TDD로 리팩토링 시작
 * States : STRIKE, SPARE와 같은 상태들을 가지고 있는 State 모음
 *          한 Frame 에 한 States 를 가지게 할 생각이며, States 안에 남은 볼링 핀 개수도 기록됨
 *          생각해보니 State를 Enum 말고 인터페이스로 만들 수 있을 것 같다는 생각이.. 음.
 * 구현체로 NormalStates 와 FinalStates 가 있음
 *
 * 나올 수 있는 TEST CASE
 * NormalStates
 * - 초기 상태에서 10개를 하면 STRIKE
 * - N개, (10 - N) 개, (N != 0) 을 하면 IN_PROGRESS, SPARE 가 States에 존재
 * - 3개 6개 처럼 10개가 안되게 볼링핀을 쓰러뜨리면 IN_PROGRESS, MISS
 * - 0개, 0개 이면 IN_PROGRESS, GUTTER
 */
@SuppressWarnings("NonAsciiCharacters")
class StatesTest {

	@Test
	void 처음에_10개를_치면_스트라이크이다() {
		// given
		States states = NormalStates.newInstance();

		// when
		states.roll(BowlingPins.of(10));

		// then
		assertThat(states).isEqualTo(NormalStates.of(0, Strike.of(10)));
	}

	@Test
	void 처음에_4개_그다음_6개를_치면_스페어이다() {
		// given
		States states = NormalStates.newInstance();

		// when
		states.roll(BowlingPins.of(4));
		states.roll(BowlingPins.of(6));

		// then
		assertThat(states).isEqualTo(NormalStates.of(0, InProgress.of(4), Spare.of(6)));
	}

	@Test
	void 처음에_5개_그다음_4개를_치면_미스이다() {
		// given
		States states = NormalStates.newInstance();

		// when
		states.roll(BowlingPins.of(5));
		states.roll(BowlingPins.of(4));

		// then
		assertThat(states).isEqualTo(NormalStates.of(0, InProgress.of(5), Miss.of(4)));
	}

	@Test
	void 처음에_0개_그다음_0개를_치면_거터이다() {
		// given
		States states = NormalStates.newInstance();

		// when
		states.roll(BowlingPins.of(5));
		states.roll(BowlingPins.of(4));

		// then
		assertThat(states).isEqualTo(NormalStates.of(0, InProgress.of(5), Miss.of(4)));
	}

	@Test
	void 스트라이크를_친_이후에_더_공을_칠_수_없다() {
		// given
		States states = NormalStates.newInstance();
		states.roll(BowlingPins.of(10));

		// when & then
		assertThatThrownBy(() -> {
			states.roll(BowlingPins.of(3));
		})
		.isInstanceOf(IllegalStateException.class);
	}

	@Test
	void 두_번_친_이후에_더_공을_칠_수_없다() {
		// given
		States states = NormalStates.newInstance();
		states.roll(BowlingPins.of(4));
		states.roll(BowlingPins.of(5));

		// when & then
		assertThatThrownBy(() -> {
			states.roll(BowlingPins.of(3));
		})
		.isInstanceOf(IllegalStateException.class);
	}

	@Test
	void 존재하는_핀보다_많은_핀을_칠_수_없다() {
		// given
		States states = NormalStates.newInstance();
		states.roll(BowlingPins.of(4));

		// when & then
		assertThatThrownBy(() -> {
			states.roll(BowlingPins.of(10));
		})
		.isInstanceOf(IllegalStateException.class);
	}

}
