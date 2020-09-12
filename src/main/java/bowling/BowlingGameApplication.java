package bowling;

import bowling.frame.Frames;
import bowling.pin.Pins;
import bowling.user.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGameApplication {

	public static void main(String[] args) {
		Player player = Player.of(InputView.inputPlayerName());
		BowlingGame bowlingGame = BowlingGame.init(player);
		OutputView.viewInit(player);

		while (!bowlingGame.isEndGame()) {
			int knockingDownPins = InputView.inputPitchingOf(bowlingGame.getCurrentFrameNo());
			Frames frames = bowlingGame.pitchBall(Pins.of(knockingDownPins));
			OutputView.viewPitchingResult(bowlingGame.getPlayer(), frames);
		}

	}
}
