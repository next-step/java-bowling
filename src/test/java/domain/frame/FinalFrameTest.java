package domain.frame;

	import domain.Pin;
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
		Assertions.assertThat(frame.getFrameResult().toString()).isEqualTo("X|X|X");
	}
}