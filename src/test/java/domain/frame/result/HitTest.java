package domain.frame.result;

import domain.Score;
import domain.frame.Frame;
import domain.frame.SecondBowlFrame;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by hspark on 22/11/2018.
 */

/**
 * - HIT (다 못쳤을 경우)
 *  10점을 못 맞췄을 경우, 다음 프레임을 요청하면 같은 프레임의 두번째 투구를 다시 돌려준다.
 */
public class HitTest {

	@Test
	public void test_다음프레임_생성() {
		FrameResult frameResult = new Hit(1, Score.ZERO);
		Frame frame = frameResult.nextGeneralFrame();
		Assertions.assertThat(frame instanceof SecondBowlFrame).isTrue();
		Assertions.assertThat(frame.getFrameNumber()).isEqualTo(1);
	}
}