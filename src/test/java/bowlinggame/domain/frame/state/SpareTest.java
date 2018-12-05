package bowlinggame.domain.frame.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.Pin;
import bowlinggame.domain.frame.Score;
import org.junit.Before;
import org.junit.Test;

public class SpareTest {

	public static final Spare newSpare = new Spare(Pin.of(3), Pin.of(7));

	private Spare spare;

	@Before
	public void setUp() {
		spare = newSpare;
	}

	@Test
	public void 스트라이크_점수_계산() {
		assertThat(spare.calculateBonus(new Strike().getScore())).isEqualTo(Score.of(20, 0));
	}

	@Test
	public void 스페어_점수_계산() {
		Spare otherSpare = newSpare;

		assertThat(spare.calculateBonus(otherSpare.getScore())).isEqualTo(Score.of(13, 0));
	}
}