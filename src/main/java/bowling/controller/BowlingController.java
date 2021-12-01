package bowling.controller;

import bowling.domain.Bowling;
import bowling.domain.Pins;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {
	public void play() {
		Player player = InputView.scanPlayer();
		Bowling bowling = Bowling.create(player);

		ResultView.print(bowling);

		while (bowling.hasNextPitching()) {
			int currentFrameIndex = bowling.getCurrentFrameIndex();
			Pins pins = InputView.scanPins(currentFrameIndex);

			bowling.bowl(pins);

			ResultView.print(bowling);
		}
	}
}
