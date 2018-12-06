package bowlinggame.domain.frame.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.Score;
import org.junit.Before;
import org.junit.Test;

public class FinalStateTest {

	private FinalState finalState;

	@Before
	public void setUp() throws Exception {
		finalState = new FinalState();
	}

	@Test
	public void 스트라이크_점수_계산() {
		finalState.roll(10);
		finalState.roll(10);
		finalState.roll(10);

		assertThat(finalState.getScore()).isEqualTo(Score.of(30));
	}

	@Test
	public void 스페어_점수_계산() {
		finalState.roll(3);
		finalState.roll(7);
		finalState.roll(10);

		assertThat(finalState.getScore()).isEqualTo(Score.of(20));
	}

	@Test
	public void 미스_점수_계산() {
		finalState.roll(3);
		finalState.roll(6);

		assertThat(finalState.getScore()).isEqualTo(Score.of(9));
	}
}