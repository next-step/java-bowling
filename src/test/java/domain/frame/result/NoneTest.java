package domain.frame.result;

import domain.Pin;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by hspark on 02/12/2018.
 */
public class NoneTest {
	@Test
	public void test_스트라이크_생성() {
		FrameResult frameResult = new None(1);
		FrameResult spare = frameResult.tryBowl(Pin.TEN);

		Assertions.assertThat(spare).isInstanceOf(Strike.class);
		Assertions.assertThat(spare.toString()).isEqualTo("X");
	}

	@Test
	public void test_히트_생성() {
		FrameResult frameResult = new None(1);

		FrameResult spare = frameResult.tryBowl(Pin.of(2));

		Assertions.assertThat(spare).isInstanceOf(Hit.class);
		Assertions.assertThat(spare.toString()).isEqualTo("2");
	}

}