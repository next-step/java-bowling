package bowling.controller;

import bowling.domain.Bowling;
import bowling.domain.common.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {

	public static void main(final String[] args) {
		// final int playerCount = InputView.playerCount();
		//
		// final List<Player> players = new ArrayList<>();
		// for (int i = 0; i < playerCount; i++) {
		// 	players.add(new Player(InputView.name()));
		// }
		//
		// final Bowling bowling = Bowling.of();

		final Player player = new Player(InputView.name());
		Bowling bowling = Bowling.of();

		OutputView.printFrames(player, bowling);
		while (bowling.possiblePitch()) {
			final int pinsCount = InputView.pinsCount(bowling.currentFrameIndex());
			bowling = bowling.pitch(pinsCount);
			OutputView.printFrames(player, bowling);
		}
	}
}
