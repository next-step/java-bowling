package controller;

import domain.bowling.BowlingGame;
import view.InputView;
import view.ResultView;

public class BowlingController {

	private BowlingController() {

	}

	public static void start() {
		int currentFrame = 1;
		BowlingGame newGame = new BowlingGame(InputView.receiveUserName());

		while (newGame.isNotEnd()) {
			newGame.roll(InputView.receiveFallenBowlingPins(currentFrame));
			currentFrame = newGame.getCurrentFrameIndex();
			ResultView.printCurrentScore(newGame.getBowlingOnBoard());
		}
	}

}
