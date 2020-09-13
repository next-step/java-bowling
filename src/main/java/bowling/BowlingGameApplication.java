package bowling;

import bowling.frame.Frames;
import bowling.pin.Pins;
import bowling.user.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGameApplication {

	public static void main(String[] args) {
		Player player = Player.of(InputView.inputPlayerName());
		Frames frames = Frames.newInstance();
		OutputView.viewInit(player);

		while (!frames.isEnd()) {
			int knockingDownPins = InputView.inputPitchingOf(frames.getCurrentFrameNo());
			frames = frames.reflect(Pins.of(knockingDownPins));
			OutputView.viewPitchingResult(player, frames);
		}

	}
}
