package bowling.controller;

import bowling.domain.game.Game;
import bowling.view.InputView;

public class BowlingController {

    private BowlingController() {
    }

    public static void game() {

        String name = InputView.inputName();
        Game game = Game.init(name);

        while (game.isNext()) {
            game.playing();
        }
    }
}
