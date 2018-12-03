package domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by hspark on 22/11/2018.
 */
public class BowlingTest {
	@Test
	public void test_시작_다음프레임() {
		Bowling bowling = new Bowling();

		// 1 Frame
		bowling.bowl(Pin.of(8));
		bowling.bowl(Pin.of(2));

		// 2 Frame
		bowling.bowl(Pin.of(2));
		int nextFrameNumber = bowling.getNextFrameNumber();
		Assertions.assertThat(nextFrameNumber).isEqualTo(2);
	}

	@Test
	public void test_게임_결과() {
		Bowling bowling = new Bowling();

		// 1 Frame
		bowling.bowl(Pin.of(8));
		bowling.bowl(Pin.of(2));

		// 2 Frame
		bowling.bowl(Pin.of(2));

		Assertions.assertThat(bowling.getBowlingScoreBoard().gameResult()).hasSize(2);
		Assertions.assertThat(bowling.getBowlingScoreBoard().scores()).hasSize(1);

	}
}