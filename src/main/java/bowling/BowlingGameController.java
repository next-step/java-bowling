package bowling;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.score.Score;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGameController {

	private final BowlingGame bowlingGame;

	public BowlingGameController(BowlingGame bowlingGame) {
		this.bowlingGame = bowlingGame;
	}

	public void playGame() {
		Player player = InputView.askPlayer();
		Frames frames = bowlingGame.startGame();

		while (frames.canPlayMore()) {
			Score score = InputView.askScore(frames.findPlayingFrame().getIndex() + 1);
			frames = bowlingGame.addScore(frames, score);
			OutputView.printFrames(player, frames);
		}
	}
}
