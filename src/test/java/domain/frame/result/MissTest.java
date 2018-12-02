package domain.frame.result;

import domain.Pin;
import domain.Score;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by hspark on 22/11/2018.
 */
public class MissTest {

	@Test
	public void test_결과생성() {
		FrameResult miss = new Miss(1, Pin.of(3), Pin.of(6));
		Assertions.assertThat(miss.getFrameNumber()).isEqualTo(1);
		Assertions.assertThat(miss.toString()).isEqualTo("3|6");
	}

	@Test
	public void test_스트라이크_점수계산() {
		FrameResult miss = new Miss(1, Pin.of(8), Pin.of(1));
		Score score = Score.STRIKE;
		score = miss.calculateScore(score);

		Assertions.assertThat(score.isScoreCalculateComplete()).isEqualTo(true);
		Assertions.assertThat(score.getScore()).isEqualTo(19);
	}
}