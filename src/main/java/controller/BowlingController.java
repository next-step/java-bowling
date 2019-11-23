package controller;

import domain.bowlling.BowlingGame;
import view.InputView;
import view.ResultView;

public class BowlingController {

	private BowlingController() {

	}

	public static void start() {
		int currentFrame = 1;
		BowlingGame newGame = new BowlingGame(InputView.receiveUserName());

		while (newGame.isNotEnd()) {
			newGame.shoot(InputView.receiveFallenBowlingPins(currentFrame));
			currentFrame = newGame.getCurrentFrame();
			ResultView.printCurrentScore(newGame.getBowlingOnBoard());
		}


	}


}
