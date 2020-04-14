package bowling.domain.controller;

import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGameController {
    public static void start(InputView inputView, OutputView outputView) {
        Player player = new Player(inputView.getPlayerName());

        while(!player.isEnd()) {
            player.play(inputView.getFelledPin(player.getCurrentFrameNumber()));

            inputView.showFrameResult(player);
        }
    }
}
