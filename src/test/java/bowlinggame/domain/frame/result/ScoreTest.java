package bowlinggame.domain.frame.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ScoreTest {

	public static final Score STRIKE = Score.of(10, Strike.getInstance());
	public static final Score MISS_FIVE = Score.of(5, Miss.getInstance(5));


	@Test
	public void 보너스_점수_계산() {
		Score score = STRIKE;

		assertThat(score.calculateBonus(Miss.getInstance(5))).isEqualTo(Score.of(15, 1));
	}

	@Test
	public void 보너스가_없으면_계산하지않음() {
		Score score = Score.init();

		assertThat(score.calculateBonus(Miss.getInstance(5))).isEqualTo(score);
	}

	@Test
	public void 보너스가없으면_점수_합계() {
		Score current = MISS_FIVE;
		Score totalScore = Score.of(50);

		assertThat(current.sum(totalScore)).isEqualTo(Score.of(55));
	}

	@Test
	public void 보너스가_있으면_점수를_합하지_않음() {
		Score current = STRIKE;
		Score totalScore = Score.of(50);

		assertThat(current.sum(totalScore)).isEqualTo(current);
	}
}