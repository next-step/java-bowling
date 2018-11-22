package domain.frame.result;

import domain.Score;
import domain.frame.FirstBowlFrame;
import domain.frame.Frame;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by hspark on 22/11/2018.
 */
public class MissTest {

	@Test
	public void test_결과생성() {
		FrameResult miss = new Miss(1, Score.of(3));
		Assertions.assertThat(miss.getScore()).isEqualTo(Score.of(3));
		Assertions.assertThat(miss.getFrameNumber()).isEqualTo(1);
	}

	@Test
	public void test_다음프레임_생성() {
		FrameResult miss = new Miss(1, Score.of(3));
		Frame frame = miss.next();
		Assertions.assertThat(frame instanceof FirstBowlFrame);
		Assertions.assertThat(frame.getFrameNumber()).isEqualTo(2);
	}

}