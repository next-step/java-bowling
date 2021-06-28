package bowling.controllers;

import bowling.domain.Game;
import bowling.domain.Player;
import bowling.views.InputView;
import bowling.views.OutputView;

public class BowlingController {
    public static void run() {
        final Player player = new Player(InputView.name());

        Game game = Game.init();
        OutputView.print(player, game);

        while (game.playing()) {
            game = game.play(InputView.knockedPinsCount(game.currentFrameNumber()));
            OutputView.print(player, game);
        }
    }
}