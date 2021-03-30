package bowling.controller;

import bowling.entity.FinalFrame;
import bowling.entity.NormalFrame;
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
			NormalFrame first = NormalFrame.ofNext(i + 1, inputView.getFrameScore(i + 1), null);
			showGame(player, first);

			if (!first.isKeepGoing()) {
				continue;
			}

			NormalFrame second = NormalFrame.ofNext(i + 1, inputView.getFrameScore(i + 1), first);
			showGame(player, second);
		}

		doFinalFrame(player);
	}

	private Player makePlayer() {
		String name = inputView.getUserName();
		return new Player(name);
	}

	private void showGame(Player player, NormalFrame normalFrame) {
		outputView.showFrames();
		player.addPlayerFrameScore(normalFrame);
		outputView.showPlayerFrames(player);
	}

	private void showGame(Player player, FinalFrame finalFrame) {
		outputView.showFrames();
		player.addPlayerFrameScore(finalFrame);
		outputView.showPlayerFrames(player);
	}

	private void doFinalFrame(Player player) {
		FinalFrame finalFrame = new FinalFrame();
		int index = 0;
		while (true) {
			finalFrame.add(index, inputView.getFrameScore(10));
			showGame(player, finalFrame);
			if (!finalFrame.isKeepGoing(index++)) {
				return;
			}
		}
	}
}
