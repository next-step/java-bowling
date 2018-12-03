package domain.frame;

import domain.Pin;
import domain.frame.state.FinalState;
import domain.frame.state.Miss;
import domain.frame.state.Spare;
import domain.frame.state.Strike;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

/**
 * Created by hspark on 30/11/2018.
 */
public class NormalFrameTest {

	@Test
	public void test_스트라이크() {
		Frame frame = Frame.first();
		frame.pitch(Pin.of(10));
		FrameResults frameResults = frame.getFrameResults();
		Assertions.assertThat(frameResults.getByFrameNumber(1).getState()).isInstanceOf(Strike.class);
	}

	@Test
	public void test_스페어() {
		Frame frame = Frame.first();
		frame.pitch(Pin.of(8));
		frame.pitch(Pin.of(2));

		FrameResults frameResults = frame.getFrameResults();
		Assertions.assertThat(frameResults.getByFrameNumber(1).getState()).isInstanceOf(Spare.class);
	}

	@Test
	public void test_일반() {
		Frame frame = Frame.first();
		frame.pitch(Pin.of(5));
		frame.pitch(Pin.of(2));

		FrameResults frameResults = frame.getFrameResults();
		Assertions.assertThat(frameResults.getByFrameNumber(1).getState()).isInstanceOf(Miss.class);
	}

	@Test
	public void test_퍼펙트게임() {
		Frame frame = Frame.first();
		while (frame.isLeftFrame()) {
			frame.pitch(Pin.TEN);
		}

		FrameResults frameResults = frame.getFrameResults();
		Assertions.assertThat(frameResults.getByFrameNumber(1).getState()).isInstanceOf(Strike.class);
		Assertions.assertThat(frameResults.getFrameResultCount()).isEqualTo(10);
		Assertions.assertThat(frameResults.getByFrameNumber(10).getState()).isInstanceOf(FinalState.class);
		Assertions.assertThat(frameResults.getByFrameNumber(10).getState().toString()).isEqualTo("X|X|X");
	}

	@Test
	public void test_퍼펙트게임_점수계산() {
		Frame frame = Frame.first();
		while (frame.isLeftFrame()) {
			frame.pitch(Pin.TEN);
		}

		List<Integer> scoreList = frame.getFrameResults().toScoreList();
		Assertions.assertThat(scoreList.get(8)).isEqualTo(270);
		Assertions.assertThat(scoreList.get(9)).isEqualTo(300);
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

		List<Integer> scoreList = frame.getFrameResults().toScoreList();
		Assertions.assertThat(scoreList.get(0)).isEqualTo(15);
		Assertions.assertThat(scoreList.get(1)).isEqualTo(30);
	}

	@Test
	public void test_점수계산가능_스트라이크() {
		Frame frame = Frame.first();
		frame.pitch(Pin.TEN);
		frame.pitch(Pin.TEN);
		frame.pitch(Pin.TEN);

		List<Integer> scoreList = frame.getFrameResults().toScoreList();
		Assertions.assertThat(scoreList.get(0)).isEqualTo(30);
	}

	@Test
	public void test_점수계산가능_스페어() {
		Frame frame = Frame.first();
		frame.pitch(Pin.of(1));
		frame.pitch(Pin.of(9));
		frame.pitch(Pin.TEN);

		List<Integer> scoreList = frame.getFrameResults().toScoreList();
		Assertions.assertThat(scoreList.get(0)).isEqualTo(20);
	}
}