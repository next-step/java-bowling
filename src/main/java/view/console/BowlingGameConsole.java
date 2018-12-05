package view.console;

import domain.Bowling;
import domain.Player;

/**
 * Created by hspark on 21/11/2018.
 */
public class BowlingGameConsole {
	public static void main(String[] args) {
		Player player = InputView.inputName();
		Bowling bowling = new Bowling(player);

		while (bowling.hasNext()) {
			int nextFrameNumber = bowling.getNextFrameNumber();
			bowling.bowl(InputView.inputScore(nextFrameNumber));
			OutputView.printBoard(bowling.getBowlingScoreBoard());
		}
	}
}
