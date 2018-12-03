package domain.frame.result;

/**
 * Created by hspark on 22/11/2018.
 */

import domain.Pin;
import domain.Score;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 - SPARE
 */
public class SpareTest {
	@Test
	public void test_스페어_생성() {
		FrameResult spare = new Spare(1, Pin.of(8));
		Assertions.assertThat(spare.toString()).isEqualTo("8|/");
	}

	@Test
	public void test_스트라이크_점수계산() {
		FrameResult spare = new Spare(1, Pin.of(8));
		Score score = Score.STRIKE;
		score = spare.calculateScore(score);

		Assertions.assertThat(score.isScoreCalculateComplete()).isEqualTo(true);
		Assertions.assertThat(score.getScore()).isEqualTo(20);
	}
}