package bowling.controller;

import bowling.entity.Player;
import bowling.views.InputView;
import bowling.views.OutputView;

public class BowlingGameController {
	private final InputView inputView;
	private final OutputView outputView;

	public BowlingGameController() {
		this.inputView = new InputView();
		this.outputView = new OutputView();
	}

	public void run() {
		Player player = makePlayer();

		for (int i = 0; i < 9; i++) {
			player.addPlayerFrameScore(i + 1, inputView.getFrameScore(i + 1));
			showGame(player);
			if (player.isKeepGoing(i + 1)) {
				player.addPlayerFrameScore(i + 1, inputView.getFrameScore(i + 1));
				showGame(player);
			}
		}

		while (true) {
			if (!player.isFinalFrameKeepGoing()) {
				break;
			}
			player.addFinalFrame(inputView.getFrameScore(10));
			showGame(player);
		}
	}

	private Player makePlayer() {
		String name = inputView.getUserName();
		return new Player(name);
	}

	private void showGame(Player player) {
		outputView.showFrames();
		outputView.showPlayerFrames(player);
	}

}
