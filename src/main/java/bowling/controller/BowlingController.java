package bowling.controller;

import java.util.List;

import bowling.domain.BowlingGame;
import bowling.domain.player.Bowler;
import bowling.view.InputView;
import bowling.view.OutputView;
import bowling.view.dto.PrintBowlerDto;

public class BowlingController {

	public static void main(final String[] args) {
		final BowlingGame bowlingGame = createBowlingGame();

		OutputView.printResultBoard(PrintBowlerDto.of(bowlingGame.getBowlers()));
		while (!bowlingGame.isFinish()) {
			final int pins = getHitPinCount(bowlingGame.getCurrentBowler());
			bowlingGame.play(pins);
			OutputView.printResultBoard(PrintBowlerDto.of(bowlingGame.getBowlers()));
		}
	}

	private static BowlingGame createBowlingGame() {
		final List<String> names = InputView.inputPlayerNames();
		return BowlingGame.start(names);
	}

	private static int getHitPinCount(final Bowler bowler) {
		return InputView.inputHitPinCount(bowler.getName());
	}
}
