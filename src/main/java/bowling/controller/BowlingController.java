package bowling.controller;

import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {
	public void run() {
		try {
			playingBowling();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	private void playingBowling() {
		Player player = new Player(InputView.inputPlayerName());
		Frames frames = Frames.init();
		ResultView.printBoard(frames, player);

		while (frames.isContinueFrame()) {
			int currentFrame = frames.currentFrame();
			frames.bowl(InputView.inputHittingNumber(currentFrame));
			ResultView.printBoard(frames, player);
		}
	}
}
