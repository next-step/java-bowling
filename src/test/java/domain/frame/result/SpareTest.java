package domain.frame.result;

import domain.Score;
import domain.frame.FinalFrame;
import domain.frame.FirstBowlFrame;
import domain.frame.Frame;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by hspark on 22/11/2018.
 */

/**
 - SPARE
    - 다음 프레임을 요청하면 다음 프레임을 생성해 돌려준다.
    - 마지막 프레임일 경우, Spare면 같은 프레임의 추가 프레임을 돌려준다.
 */
public class SpareTest {
	@Test
	public void test_다음프레임_생성() {
		FrameResult spare = new Spare(1, Score.of(8));
		Frame frame = spare.next();
		Assertions.assertThat(frame instanceof FirstBowlFrame);
		Assertions.assertThat(frame.getFrameNumber()).isEqualTo(2);
	}

	@Test
	public void test_마지막프레임_생성() {
		FrameResult spare = new Spare(10, Score.of(8));
		Frame frame = spare.next();
		Assertions.assertThat(frame instanceof FinalFrame);
		Assertions.assertThat(frame.getFrameNumber()).isEqualTo(10);
	}
}