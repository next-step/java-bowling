package bowling.step2;

import bowling.step2.domain.Game;
import bowling.step2.view.InputView;
import bowling.step2.view.ResultView;

public class BowlingApplication {

	public static void main(String[] args) {
		String playerName = InputView.inputPlayerName();

		Game game = Game.startGame(playerName);
		ResultView.printInitBowlingScore(game);
	}
}
