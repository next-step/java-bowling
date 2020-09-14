package bowling;

import java.util.List;

import bowling.frame.Frames;
import bowling.pin.Pins;
import bowling.user.Players;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGameApplication {

	public static void main(String[] args) {
		//Player player = Player.of(InputView.inputPlayerName());
		int countOfPlayer = InputView.inputCountOfPlayer();
		Players players = Players.of(InputView.inputPlayerNames(countOfPlayer));
		BowlingGame bowlingGame = BowlingGame.init(players);
		OutputView.viewInit(players);

		while (!bowlingGame.isEndGame()) {
			int knockingDownPins = InputView.inputPitchingOf(bowlingGame.getCurrentPlayer());
			List<Frames> framesList = bowlingGame.pitchBall(Pins.of(knockingDownPins));
			OutputView.viewPitchingResults(players, framesList);
			//OutputView.viewPitchingResult(bowlingGame.getPlayer(), frames);
		}

	}
}
