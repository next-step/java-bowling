package bowlinggame.domain.frame.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.Pin;
import bowlinggame.domain.frame.Score;
import org.junit.Before;
import org.junit.Test;

public class MissTest {

	private Miss miss;

	@Before
	public void setUp() {
		miss = new Miss(Pin.of(3), Pin.of(5));
	}

	@Test
	public void 스트라이크_추가_점수_계산() {
		Score score = Score.of(10, 2);
		assertThat(miss.calculateBonus(score)).isEqualTo(Score.of(18, 0));
	}

	@Test
	public void 스페어_추가_점수_계산() {
		Score score = Score.of(10, 1);
		assertThat(miss.calculateBonus(score)).isEqualTo(Score.of(13, 0));
	}
}