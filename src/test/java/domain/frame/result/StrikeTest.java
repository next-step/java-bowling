package domain.frame.result;

import domain.frame.FinalFrame;
import domain.frame.Frame;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by hspark on 22/11/2018.
 */

/**
 * - STRIKE (한번에 다 쳤을경우)
 *  - STRIKE가 나왔을 경우, 다음 프레임 요청 시 다음 프레임을 생성해 돌려준다.
 *  - 마지막 프레임일 경우, Spare면 같은 프레임의 추가 프레임을 돌려준다.
 */
public class StrikeTest {

	@Test
	public void test_다음프레임_생성() {
		FrameResult strike = new Strike(10);
		Frame frame = strike.next();
		Assertions.assertThat(frame).isInstanceOf(FinalFrame.class);
		Assertions.assertThat(frame.getFrameNumber()).isEqualTo(10);
	}
}