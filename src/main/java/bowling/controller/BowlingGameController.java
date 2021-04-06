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

		while(player.isKeepGoing()) {
			player.addPlayerFrameScore(inputView.getFrameScore(player.currentPlayerFrameIndex()));
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
		outputView.showPlayerFrameScore(player);
	}

}
