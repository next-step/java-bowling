package bowling.controller;

import java.util.List;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.domain.Pins;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {
	public void play() {
		List<Player> players = InputView.scanPlayers();
		BowlingGames bowlingGames = BowlingGames.create(players);

		ResultView.print(bowlingGames.getValues());

		while (bowlingGames.hasNextPitching()) {
			bowlingGames.increaseIndexIfFrameEnd();
			bowlingGames.getValues()
				.forEach(bowling -> play(bowlingGames, bowling));
		}
	}

	private void play(BowlingGames bowlingGames, BowlingGame bowling) {
		while (bowling.isFrameRunning(bowlingGames.getCurrentIndex())) {
			String playerName = bowling.getPlayerName();
			Pins pins = InputView.scanPins(playerName);

			bowling.bowl(pins);

			ResultView.print(bowlingGames.getValues());
		}
	}
}
