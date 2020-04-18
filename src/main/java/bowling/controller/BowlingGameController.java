package bowling.controller;

import bowling.domain.state.PinCount;
import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGameController {
    public static void start(InputView inputView, OutputView outputView) {
        Player player = new Player(inputView.getPlayerName());

        while (!player.isEnd()) {
            int felledPin = inputView.getFelledPin(player.getCurrentFrameNumber());
            player.play(PinCount.create(felledPin));

            outputView.showFrameResult(player);
            outputView.showFrameScore(player);
        }
    }
}
