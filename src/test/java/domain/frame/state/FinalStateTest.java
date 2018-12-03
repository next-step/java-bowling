package domain.frame.state;

import domain.Pin;
import domain.Score;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by hspark on 02/12/2018.
 */

/**
 * 스트라이크만 쳤을 경우 세번 가능
 * 스페어일 경우 한번 더 가능
 * 그 외의 경우 다 칠 경우 끝.
 */
public class FinalStateTest {
	@Test
	public void test_스트라이크_세번_가능여부() {
		State state = new FinalState();
		state.tryBowl(Pin.TEN);
		Assertions.assertThat(state.isFinished()).isFalse();
		state.tryBowl(Pin.TEN);
		Assertions.assertThat(state.isFinished()).isFalse();
		state.tryBowl(Pin.TEN);
		Assertions.assertThat(state.isFinished()).isTrue();
		Assertions.assertThat(state.toString()).isEqualTo("X|X|X");
	}

	@Test
	public void test_스페어_보너스_가능여부() {
		State state = new FinalState();
		state.tryBowl(Pin.of(1));
		Assertions.assertThat(state.isFinished()).isFalse();
		state.tryBowl(Pin.of(9));
		Assertions.assertThat(state.isFinished()).isFalse();
		state.tryBowl(Pin.TEN);
		Assertions.assertThat(state.isFinished()).isTrue();
		Assertions.assertThat(state.toString()).isEqualTo("1|/|X");
	}

	@Test
	public void test_보너스불가() {
		State state = new FinalState();
		state.tryBowl(Pin.of(1));
		Assertions.assertThat(state.isFinished()).isFalse();
		state.tryBowl(Pin.of(8));
		Assertions.assertThat(state.isFinished()).isTrue();
		Assertions.assertThat(state.toString()).isEqualTo("1|8");
	}

	@Test
	public void test_스트라이크_점수계산() {
		State state = new FinalState();
		state.tryBowl(Pin.of(1));
		state.tryBowl(Pin.of(8));
		Score score = Score.STRIKE;
		Score actualScore = state.calculateScore(score);
		Assertions.assertThat(actualScore.getScore()).isEqualTo(19);
	}
}