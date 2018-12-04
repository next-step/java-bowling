package domain;

import domain.frame.FrameResult;
import domain.frame.FrameResults;
import domain.frame.state.*;
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
		State state = new Strike();
		State state2 = new Miss(Pin.of(5), Pin.of(2));
		State state3 = new Spare(Pin.of(5));
		FrameResults frameResults = new FrameResults(Arrays.asList(
			new FrameResult(state, 17),
			new FrameResult(state2, 7),
			new FrameResult(state3, FrameResult.NON_CALCULATE_SCORE)));
		List<String> scores = frameResults.toResultList();
		Assertions.assertThat(scores).hasSize(3);
		Assertions.assertThat(scores).contains("X", "5|2", "5|/");
	}
}