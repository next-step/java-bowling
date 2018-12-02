import domain.Bowling;
import domain.Player;
import view.InputView;
import view.OutputView;

/**
 * Created by hspark on 21/11/2018.
 */
public class BowlingGameConsole {
	public static void main(String[] args) {
		Player player = InputView.inputName();
		Bowling bowling = new Bowling();

		while (bowling.hasNext()) {
			int nextFrameNumber = bowling.getNextFrameNumber();
			bowling.bowl(InputView.inputScore(nextFrameNumber));
			OutputView.printBoard(player, bowling.getBowlingScoreBoard());
		}
	}
}
