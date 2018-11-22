package domain.frame;

import domain.Score;
import domain.frame.result.FrameResult;
import domain.frame.result.Gutter;
import domain.frame.result.Hit;
import domain.frame.result.Strike;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by hspark on 22/11/2018.
 */

/**
 * - 첫번째 투구 객체의 결과로는 HIT와 STRIKE, GUTTER를 반환한다.
 *  - HIT
 *     - 10점을 못 맞췄을 경우 반환.
 * - STRIKE
 *     - 10점이 나왔을 경우 반환.
 * - GUTTER
 *     - 0점일 경우
 */
public class FirstBowlFrameTest {
	@Test
	public void test_첫번째투구_생성() {
		Frame frame = Frame.first();
		assertThat(frame).isInstanceOf(FirstBowlFrame.class);
	}

	@Test
	public void test_투구후_결과생성() {
		Frame frame = Frame.first();
		FrameResult frameResult = frame.pitch(Score.of(1));
		assertThat(frameResult).isInstanceOf(Hit.class);
	}

	@Test
	public void test_투구후_스트라이크_생성() {
		Frame frame = Frame.first();
		FrameResult frameResult = frame.pitch(Score.of(10));
		assertThat(frameResult).isInstanceOf(Strike.class);
	}

	@Test
	public void test_투구후_거터생성() {
		Frame frame = Frame.first();
		FrameResult frameResult = frame.pitch(Score.of(0));
		assertThat(frameResult).isInstanceOf(Gutter.class);
	}
}