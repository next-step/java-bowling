package bowling;

import java.util.List;

import bowling.frame.Frames;
import bowling.pin.Pins;
import bowling.score.ScoringBoards;
import bowling.user.Players;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGameApplication {

	public static void main(String[] args) {
		int countOfPlayer = InputView.inputCountOfPlayer();
		Players players = Players.of(InputView.inputPlayerNames(countOfPlayer));
		ScoringBoards scoringBoards = ScoringBoards.of(players);
		OutputView.viewInit(players);

		while (!scoringBoards.isEnd()) {
			int knockingDownPins = InputView.inputPitchingOf(scoringBoards.getCurrentPlayer());
			List<Frames> framesList = scoringBoards.reflect(Pins.of(knockingDownPins));
			OutputView.viewPitchingResults(players, framesList);
		}

	}
}
