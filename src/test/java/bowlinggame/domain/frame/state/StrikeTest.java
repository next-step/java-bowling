package bowlinggame.domain.frame.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.Score;
import org.junit.Before;
import org.junit.Test;

public class StrikeTest {


	private Strike strike;

	@Before
	public void setUp() throws Exception {
		strike = new Strike();
	}

	@Test
	public void 스트라이크_점수_계산() {
		Strike other = new Strike();
		assertThat(strike.calculateBonus(other.getScore())).isEqualTo(Score.of(20, 1));
	}

	@Test
	public void 스페어_점수_계산() {
		Spare other = SpareTest.newSpare;

		assertThat(strike.calculateBonus(other.getScore())).isEqualTo(Score.of(20, 0));
	}
}