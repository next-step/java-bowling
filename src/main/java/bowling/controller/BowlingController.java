package bowling.controller;

import bowling.domain.game.Game;
import bowling.view.InputView;
import bowling.view.OutputVIew;

public class BowlingController {

    private BowlingController() {
    }

    public static void game() {

        String name = InputView.inputName();
        Game game = Game.init(name);
        OutputVIew.printGame(game);

        while (game.isNext()) {
            int pins = InputView.inputPins(game.currentRound());
            game.bowling(pins);
            OutputVIew.printGame(game);
            OutputVIew.printScore(game);
        }
    }
}
