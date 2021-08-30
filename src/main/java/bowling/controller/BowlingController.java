package bowling.controller;

import bowling.domain.Bowling;
import bowling.domain.common.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {

	public static void main(final String[] args) {
		final Player player = new Player(InputView.name());
		Bowling bowling = Bowling.of();

		OutputView.printFrames(player, bowling);
		while (bowling.possiblePitch()) {
			final int falledPinsCount = InputView.falledPinsCount(bowling.currentFrameIndex());
			bowling = bowling.pitch(falledPinsCount);
			OutputView.printFrames(player, bowling);
		}
	}
}
