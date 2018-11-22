package domain.frame;

import domain.Score;
import domain.frame.result.FrameResult;
import domain.frame.result.Gutter;
import domain.frame.result.Miss;
import domain.frame.result.Spare;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by hspark on 22/11/2018.
 */

/**
 * - 두번째 투구 객체의 결과로 SPARE, MISS, GUTTER를 반환한다.
 *     - 두번째 프레임 결과 객체는 무조건 다음 프레임의 객체를 만들어 돌려준다.
 * - SPARE
 *     - 두번째 투구에서 10점이 되었을 때 반환.
 * - MISS
 *     - 합쳐서 10점이 안되었을 때 반환.
 * - GUTTER
 *     - 0점일 때 반환.
 */
public class SecondBowlFrameTest {

	@Test
	public void test_스페어() {
		SecondBowlFrame secondBowlFrame = new SecondBowlFrame(1, Score.of(8));
		FrameResult frameResult = secondBowlFrame.pitch(Score.of(2));
		Assertions.assertThat(frameResult).isInstanceOf(Spare.class);
	}

	@Test
	public void test_MISS() {
		SecondBowlFrame secondBowlFrame = new SecondBowlFrame(1, Score.of(8));
		FrameResult frameResult = secondBowlFrame.pitch(Score.of(1));
		Assertions.assertThat(frameResult).isInstanceOf(Miss.class);
	}

	@Test
	public void test_거터() {
		SecondBowlFrame secondBowlFrame = new SecondBowlFrame(1, Score.of(0));
		FrameResult frameResult = secondBowlFrame.pitch(Score.of(0));
		Assertions.assertThat(frameResult).isInstanceOf(Gutter.class);
	}
}