package bowling.controller;

import bowling.domain.Frames;
import bowling.domain.Name;
import bowling.domain.Pitch;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {

	public static void main(final String[] args) {
		final Player player = Player.of(Name.of(InputView.inputPlayerName()));
		final Frames frames = new Frames();

		while (!frames.isEnd()) {
			frames.pitch(new Pitch(InputView.inputPitch(frames.currentIndex())));
			ResultView.printResultBoard(player, frames);
		}
	}
}
