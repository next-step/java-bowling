package bowling.controller;

import bowling.model.BowlGame;
import bowling.model.Count;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Controller {

	public void run() {
		Count count = new Count(InputView.inputPlayerCount());
		String[] players = InputView.inputPlayerName(count);

		BowlGame bowlGame = BowlGame.createBowlGame(players);

		while (bowlGame.isContinueGame()) {
			OutputView.printScoreBoard(bowlGame.getPlayers());
			bowlGame.playBowling(InputView.inputStrikeNumber(bowlGame.turnPlayer()));
		}
		OutputView.printScoreBoard(bowlGame.getPlayers());
	}
}
