package domain;

import domain.frame.result.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hspark on 22/11/2018.
 */
public class BowlingPinBoardTest {

	@Test
	public void test_게임보드생성() {
		FrameResult frameResult = new Strike(1);
		FrameResult frameResult2 = new Miss(2, Pin.of(5), Pin.of(2));
		FrameResult frameResult3 = new Spare(3, Pin.of(5));
		FrameResults frameResults = new FrameResults(Arrays.asList(frameResult, frameResult2, frameResult3));
		BowlingScoreBoard bowlingScoreBoard = new BowlingScoreBoard(frameResults, null);
		List<String> scores = bowlingScoreBoard.gameResult();
		Assertions.assertThat(scores).hasSize(3);
		Assertions.assertThat(scores).contains("X", "5|2", "5|/");
	}
}