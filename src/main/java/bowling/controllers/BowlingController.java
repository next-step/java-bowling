package bowling.controllers;

import bowling.domain.Bowling;
import bowling.domain.Player;
import bowling.views.InputView;
import bowling.views.OutputView;

public class BowlingController {
    public static void run() {
        final Player player = new Player(InputView.name());

        Bowling bowling = Bowling.init();
        OutputView.print(player, bowling);

        while (bowling.playing()) {
            bowling = bowling.play(InputView.knockedPinsCount(bowling.currentFrameIndex()));
            OutputView.print(player, bowling);
        }
    }
}