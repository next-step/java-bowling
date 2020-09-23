package bowling.domain.state;

import bowling.domain.DownedPinCount;
import bowling.domain.score.GroundScore;
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
		State strike = state.roll(DownedPinCount.fromDownCount(10), GroundScore.getInstance());
		assertThat(DownedPinCount.fromDownCount(10)).isEqualTo(DownedPinCount.fromDownCount(10));
		assertThat(strike).isEqualTo(new Strike(DownedPinCount.fromDownCount(10), GroundScore.getInstance()));

		State playing = state.roll(DownedPinCount.fromDownCount(5), GroundScore.getInstance());
		assertThat(playing).isEqualTo(new Playing(DownedPinCount.fromDownCount(5), GroundScore.getInstance()));

		State spare = playing.roll(DownedPinCount.fromDownCount(5),GroundScore.getInstance());
		assertThat(spare).isEqualTo(new Spare(DownedPinCount.fromDownCount(5), DownedPinCount.fromDownCount(5), GroundScore.getInstance()));

		State open = playing.roll(DownedPinCount.fromDownCount(4), GroundScore.getInstance());
		assertThat(open).isEqualTo(new Open(DownedPinCount.fromDownCount(5), DownedPinCount.fromDownCount(4), GroundScore.getInstance()));
	}

	@DisplayName("Strike, Spare, Open 상태만 종료 상태로 반환하는지 테스트")
	@Test
	void isDone() {
		assertThat(state.isDone()).isFalse();

		State strike = state.roll(DownedPinCount.fromDownCount(10), GroundScore.getInstance());
		assertThat(strike.isDone()).isTrue();

		State playing = state.roll(DownedPinCount.fromDownCount(5), GroundScore.getInstance());
		assertThat(playing.isDone()).isFalse();

		State spare = playing.roll(DownedPinCount.fromDownCount(5), GroundScore.getInstance());
		assertThat(spare.isDone()).isTrue();

		State open = playing.roll(DownedPinCount.fromDownCount(4), GroundScore.getInstance());
		assertThat(open.isDone()).isTrue();
	}
}
