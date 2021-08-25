package bowling.controller;

import bowling.model.Name;
import bowling.model.Player;
import bowling.model.frame.Frames;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Controller {

	public void run() {
		Player player = new Player(new Name(InputView.inputPlayerName()));
		Frames frames = Frames.initCreateFrames();

		while (!frames.isContinueGame()) {
			OutputView.printScoreBoard(player, frames);
			frames.playBowling(InputView.inputStrikeNumber(frames.getPresentFrame()));
		}
		OutputView.printScoreBoard(player, frames);
	}
}
