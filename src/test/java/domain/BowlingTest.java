package domain;

import domain.frame.result.FrameResult;
import domain.frame.result.Hit;
import domain.frame.result.Strike;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by hspark on 22/11/2018.
 */
public class BowlingTest {
	@Test
	public void test_시작_스트라이크() {
		Bowling bowling = new Bowling();
		bowling.bowl(Score.of(10));
		FrameResult frameResult = bowling.getLastFrameResult();
		int nextFrameNumber = bowling.getNextFrameNumber();
		Assertions.assertThat(nextFrameNumber).isEqualTo(2);
		Assertions.assertThat(frameResult).isInstanceOf(Strike.class);
	}

	@Test
	public void test_시작_히트() {
		Bowling bowling = new Bowling();
		bowling.bowl(Score.of(5));
		FrameResult frameResult = bowling.getLastFrameResult();
		int nextFrameNumber = bowling.getNextFrameNumber();
		Assertions.assertThat(nextFrameNumber).isEqualTo(1);
		Assertions.assertThat(frameResult).isInstanceOf(Hit.class);

	}
}