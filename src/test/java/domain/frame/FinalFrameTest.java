package domain.frame;

	import domain.Pin;
	import domain.Score;
	import org.assertj.core.api.Assertions;
	import org.junit.Test;

/**
 * Created by hspark on 30/11/2018.
 */
public class FinalFrameTest {
	@Test
	public void test_스트라이크_세번() {
		Frame frame = new FinalFrame();
		while (frame.isLeftFrame()) {
			frame.pitch(Pin.TEN);
		}
		Assertions.assertThat(frame.getState().toString()).isEqualTo("X|X|X");
	}

	@Test
	public void test_직전점수계산() {
		Frame frame = new FinalFrame();
		frame.pitch(Pin.of(7));
		Score score = frame.calculateScore(Score.SPARE);
		Assertions.assertThat(score.getScore()).isEqualTo(17);
	}
}