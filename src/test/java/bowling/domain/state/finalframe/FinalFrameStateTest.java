package bowling.domain.state.finalframe;

import bowling.domain.DownedPinCount;
import bowling.domain.score.GroundScore;
import bowling.domain.state.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.DownedPinCount.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameStateTest {

	private State init;

	@BeforeEach
	void setup() {
		init = FinalFrameInitState.getInstance();
	}

	@DisplayName("싱글턴 확인")
	@Test
	void construct() {
		assertThat(init).isEqualTo(FinalFrameInitState.getInstance());
		assertThat(init.isDone()).isFalse();
	}

	@DisplayName("볼링공을 굴린 후 init -> firstState 확인")
	@Test
	void initToFirstState() {
		State firstState = init.roll(DownedPinCount.fromDownCount(10), GroundScore.getInstance());
		assertThat(firstState instanceof FinalFrameFirstState).isTrue();
		assertThat(firstState.isDone()).isFalse();
	}

	@DisplayName("볼링공을 굴린 후 firstState => secondState 확인 && 보너스가 없는경우 종료로 나타나는지 확인")
	@Test
	void firstStateToSecondState() {
		State firstState = init.roll(DownedPinCount.fromDownCount(2), GroundScore.getInstance());
		State secondState = firstState.roll(DownedPinCount.fromDownCount(7), firstState.getScore());
		assertThat(secondState instanceof FinalFrameSecondState).isTrue();
		assertThat(secondState.isDone()).isTrue();
	}

	@DisplayName("스트라이크가 포함된 경우 보너스 까지 진행되는지 확인 && 보너스 투구 후에는 종료로 나타나는지 확인")
	@Test
	void ifStrikeThenHavingBonus() {
		State first = init.roll(DownedPinCount.fromDownCount(10), GroundScore.getInstance());
		State second = first.roll(DownedPinCount.fromDownCount(0), first.getScore());
		assertThat(second.isDone()).isFalse();
		State bonus = second.roll(DownedPinCount.fromDownCount(10), second.getScore());
		assertThat(bonus instanceof FinalFrameBonusState).isTrue();
		assertThat(bonus.isDone()).isTrue();
	}

	@DisplayName("스페어인 경우 보너스 까지 진행되는지 확인 && 보너스 투구 후에는 종료로 나타나는지 확인")
	@Test
	void ifSpareThenHavingBonus() {
		State first = init.roll(DownedPinCount.fromDownCount(4), GroundScore.getInstance());
		State second = first.roll(DownedPinCount.fromDownCount(6), first.getScore());
		assertThat(second.isDone()).isFalse();
		State bonus = second.roll(DownedPinCount.fromDownCount(10), second.getScore());
		assertThat(bonus instanceof FinalFrameBonusState).isTrue();
		assertThat(bonus.isDone()).isTrue();
	}
}
