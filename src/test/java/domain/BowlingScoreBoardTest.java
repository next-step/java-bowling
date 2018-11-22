package domain;

import domain.frame.result.FrameResult;
import domain.frame.result.Hit;
import domain.frame.result.Spare;
import domain.frame.result.Strike;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hspark on 22/11/2018.
 */
public class BowlingScoreBoardTest {

	@Test
	public void test_게임보드생성() {
		FrameResult frameResult = new Strike(1);
		FrameResult frameResult2 = new Hit(2, Score.of(5));
		FrameResult frameResult3 = new Spare(2, Score.of(5));
		BowlingScoreBoard bowlingScoreBoard = new BowlingScoreBoard(Arrays.asList(frameResult, frameResult2, frameResult3));
		List<String> scores = bowlingScoreBoard.scores();
		Assertions.assertThat(scores).hasSize(2);
		Assertions.assertThat(scores).contains("X", "5|/");
	}
}