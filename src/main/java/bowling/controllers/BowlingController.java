package bowling.controllers;

import bowling.domain.Bowling;
import bowling.domain.Player;
import bowling.views.InputView;
import bowling.views.OutputView;

public class BowlingController {
    public static void run() {
        final Player player = new Player(InputView.name());

        Bowling.from(InputView.knockedPinsInput(), OutputView.framesOutput(player));
    }
}