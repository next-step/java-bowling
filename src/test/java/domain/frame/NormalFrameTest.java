package domain.frame;

import domain.FrameScores;
import domain.Pin;
import domain.frame.result.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by hspark on 30/11/2018.
 */
public class NormalFrameTest {

	@Test
	public void test_스트라이크() {
		Frame frame = Frame.first();
		frame.pitch(Pin.of(10));
		FrameResults frameResults = frame.getFrameResults();
		Assertions.assertThat(frameResults.getByFrameNumber(1)).isInstanceOf(Strike.class);
	}

	@Test
	public void test_스페어() {
		Frame frame = Frame.first();
		frame.pitch(Pin.of(8));
		frame.pitch(Pin.of(2));

		FrameResults frameResults = frame.getFrameResults();
		Assertions.assertThat(frameResults.getByFrameNumber(1)).isInstanceOf(Spare.class);
	}

	@Test
	public void test_일반() {
		Frame frame = Frame.first();
		frame.pitch(Pin.of(5));
		frame.pitch(Pin.of(2));

		FrameResults frameResults = frame.getFrameResults();
		Assertions.assertThat(frameResults.getByFrameNumber(1)).isInstanceOf(Miss.class);
	}

	@Test
	public void test_퍼펙트게임() {
		Frame frame = Frame.first();
		while (frame.isLeftFrame()) {
			frame.pitch(Pin.TEN);
		}

		FrameResults frameResults = frame.getFrameResults();
		Assertions.assertThat(frameResults.getByFrameNumber(1)).isInstanceOf(Strike.class);
		Assertions.assertThat(frameResults.getFrameResultCount()).isEqualTo(10);
		Assertions.assertThat(frameResults.getByFrameNumber(10)).isInstanceOf(FinalFrameResult.class);
		Assertions.assertThat(frameResults.getByFrameNumber(10).toString()).isEqualTo("X|X|X");
	}

	@Test
	public void test_퍼펙트게임_점수계산() {
		Frame frame = Frame.first();
		while (frame.isLeftFrame()) {
			frame.pitch(Pin.TEN);
		}

		FrameScores frameScores = frame.getScores();
		Assertions.assertThat(frameScores.getTotalScoreToFrameNumber(9)).isEqualTo(270);
		Assertions.assertThat(frameScores.getTotalScoreToFrameNumber(10)).isEqualTo(300);
	}

	@Test
	public void test_점수계산() {
		Frame frame = Frame.first();
		frame.pitch(Pin.of(5));
		frame.pitch(Pin.of(5));
		frame.pitch(Pin.of(5));
		frame.pitch(Pin.of(5));
		frame.pitch(Pin.of(5));
		frame.pitch(Pin.of(5));

		FrameScores scores = frame.getScores();
		Assertions.assertThat(scores.getTotalScoreToFrameNumber(1)).isEqualTo(15);
		Assertions.assertThat(scores.getTotalScoreToFrameNumber(2)).isEqualTo(30);
	}

	@Test
	public void test_점수계산가능_스트라이크() {
		Frame frame = Frame.first();
		frame.pitch(Pin.TEN);
		frame.pitch(Pin.TEN);
		frame.pitch(Pin.TEN);

		FrameScores scores = frame.getScores();
		Assertions.assertThat(scores.getTotalScoreToFrameNumber(1)).isEqualTo(30);
	}

	@Test
	public void test_점수계산가능_스페어() {
		Frame frame = Frame.first();
		frame.pitch(Pin.of(1));
		frame.pitch(Pin.of(9));
		frame.pitch(Pin.TEN);

		FrameScores scores = frame.getScores();
		Assertions.assertThat(scores.getTotalScoreToFrameNumber(1)).isEqualTo(20);
	}
}