package controller;

import domain.UserNumber;
import domain.bowling.BowlingGame;
import view.InputView;
import view.ResultView;

public class BowlingController {

	private BowlingController() {

	}

	public static void start() {
		UserNumber userNumber = InputView.receiveUserNumber();
		BowlingGame newGame = new BowlingGame(userNumber);
		for (int i = 0, end = userNumber.getUserNumber(); i < end; i++) {
			newGame.enroll(InputView.receiveUserName());
		}

		while (newGame.isNotEnd()) {
			newGame.roll(InputView.receiveFallenBowlingPins(newGame.getUserName()));
			ResultView.printCurrentScore(newGame.getBowlingOnBoard());
		}
	}

}
