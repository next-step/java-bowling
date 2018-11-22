package domain.frame.result;

import domain.Score;
import domain.frame.FirstBowlFrame;
import domain.frame.Frame;
import domain.frame.SecondBowlFrame;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by hspark on 22/11/2018.
 */

/**
 * - GUTTER
 *     - 0점인 경우
 *     - 첫번째 투구일 경우 두번째 투구를, 두번째 투구일 경우엔 다음 프레임을 전달한다.
 *
 */
public class GutterTest {

	@Test
	public void test_거터생성() {
		FrameResult gutter = new Gutter(1, false);
		Assertions.assertThat(gutter.getScore()).isEqualTo(Score.ZERO);
	}

	@Test
	public void test_다음프레임생성() {
		FrameResult gutter = new Gutter(1, false);
		Frame frame = gutter.next();
		Assertions.assertThat(frame).isInstanceOf(FirstBowlFrame.class);
		Assertions.assertThat(frame.getFrameNumber()).isEqualTo(2);
	}

	@Test
	public void test_다음투구생성() {
		FrameResult gutter = new Gutter(1, true);
		Frame frame = gutter.next();
		Assertions.assertThat(frame).isInstanceOf(SecondBowlFrame.class);
		Assertions.assertThat(frame.getFrameNumber()).isEqualTo(1);
	}
}