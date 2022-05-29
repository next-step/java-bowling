package bowling;

import bowling.frame.Frames;
import bowling.player.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingApplication {

	private static final InputView INPUT_VIEW = new InputView();
	private static final OutputView OUTPUT_VIEW = new OutputView();

	public static void main(String[] args) {
		Player player = new Player(INPUT_VIEW.askPlayerName());
		Frames frames = Frames.start();

		OUTPUT_VIEW.showResult(player, frames);

		while (!frames.isEnd()) {
			int frameNumber = frames.frameNumber();
			frames.throwBowl(INPUT_VIEW.askThrowCount(frameNumber));
			OUTPUT_VIEW.showResult(player, frames);
		}
	}
}
