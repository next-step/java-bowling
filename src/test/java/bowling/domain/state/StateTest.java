package bowling.domain.state;

import bowling.domain.DownedPinCount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StateTest {

	private State state;

	@BeforeEach
	void setup() {
		state = InitState.getInstance();
	}

	@DisplayName("init 객체 싱글턴 검증 테스트")
	@Test
	void initialize() {
		assertThat(state).isEqualTo(InitState.getInstance());
	}

	@DisplayName("주어지는 핀 개수에 따라 상태가 알맞게 변하는지 테스트")
	@Test
	void roll() {
		State strike = state.roll(DownedPinCount.TEN);
		assertThat(strike).isEqualTo(new Strike(DownedPinCount.TEN));

		State playing = state.roll(DownedPinCount.FIVE);
		assertThat(playing).isEqualTo(new Playing(DownedPinCount.FIVE));

		State spare = playing.roll(DownedPinCount.FIVE);
		assertThat(spare).isEqualTo(new Spare(DownedPinCount.FIVE, DownedPinCount.FIVE));

		State open = playing.roll(DownedPinCount.FOUR);
		assertThat(open).isEqualTo(new Open(DownedPinCount.FIVE, DownedPinCount.FOUR));
	}

	@DisplayName("Strike, Spare, Open 상태만 종료 상태로 반환하는지 테스트")
	@Test
	void isDone() {
		assertThat(state.isDone()).isFalse();

		State strike = state.roll(DownedPinCount.TEN);
		assertThat(strike.isDone()).isTrue();

		State playing = state.roll(DownedPinCount.FIVE);
		assertThat(playing.isDone()).isFalse();

		State spare = playing.roll(DownedPinCount.FIVE);
		assertThat(spare.isDone()).isTrue();

		State open = playing.roll(DownedPinCount.FOUR);
		assertThat(open.isDone()).isTrue();
	}
}
