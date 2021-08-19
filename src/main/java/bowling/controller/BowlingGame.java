package bowling.controller;

import bowling.domain.Player;
import bowling.domain.common.Name;
import bowling.domain.frame.Frames;
import bowling.domain.pitch.Pitch;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {

    public static void main(final String[] args) {
        final Player player = Player.of(Name.of(InputView.inputPlayerName()));
        final Frames frames = Frames.of();

        while (!frames.isEnd()) {
            frames.pitch(Pitch.first(InputView.inputPitch(frames.currentIndex())));
            ResultView.printResultBoard(player, frames);
        }
    }
}
