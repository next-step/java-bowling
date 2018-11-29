package bowlinggame.domain;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.FrameNumber;
import bowlinggame.domain.frame.FrameResult;
import bowlinggame.domain.frame.Game;
import bowlinggame.domain.frame.NormalFrame;
import bowlinggame.domain.frame.result.Strike;
import org.junit.Test;

public class GameTest {

	@Test
	public void 프레임_진행확인() {
		Game game = new Game(asList(
			new NormalFrame(FrameNumber.first(), new FrameResult())
	));
		assertThat(game.isFrameOver(FrameNumber.of(1))).isFalse();
	}

	@Test
	public void 프레임_종료확인() {
		Game game = new Game(asList(
				new NormalFrame(FrameNumber.first(), new FrameResult(asList(Strike.getInstance())))
		));
		assertThat(game.isFrameOver(FrameNumber.of(1))).isTrue();
	}
}