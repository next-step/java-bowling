package domain.frame.result;

/**
 * Created by hspark on 22/11/2018.
 */

import domain.Pin;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * - HIT (다 못쳤을 경우)
 *  10점을 못 맞췄을 경우, 다음 결과로 스페어, 미스를 생성 할 수 있다.
 */
public class HitTest {

	@Test
	public void test_스페어_생성() {
		FrameResult frameResult = new Hit(1, Pin.of(8));

		FrameResult spare = frameResult.tryBowl(Pin.of(2));
		Assertions.assertThat(spare).isInstanceOf(Spare.class);
		Assertions.assertThat(spare.toString()).isEqualTo("8|/");
	}

	@Test
	public void test_미스_생성() {
		FrameResult frameResult = new Hit(1, Pin.of(8));
		FrameResult spare = frameResult.tryBowl(Pin.of(1));

		Assertions.assertThat(spare).isInstanceOf(Miss.class);
		Assertions.assertThat(spare.toString()).isEqualTo("8|1");
	}

	@Test
	public void test_거터_생성() {
		FrameResult frameResult = new Hit(1, Pin.of(8));

		FrameResult spare = frameResult.tryBowl(Pin.ZERO);

		Assertions.assertThat(spare).isInstanceOf(Miss.class);
		Assertions.assertThat(spare.toString()).isEqualTo("8|-");
	}

}