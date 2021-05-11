package bowling.controller;


import bowling.domain.Player;
import bowling.domain.frame.*;
import bowling.view.InputView;
import bowling.view.ResultView;


public class BowlingController {

    public static Player createPlayer() {
        return new Player(InputView.getPlayer());
    }

    public static void start(Player player) {
        Frame frame = new Frame(NormalRound.createOf(), null);
        ResultView.printRound();
        ResultView.printResult(player.getName());

        while (!frame.isEnd()) {
            int frameRoundCount = frame.getFrameRoundCount();
            frame = frame.pitch(InputView.getPitch(frameRoundCount));

            ResultView.printBowlingResult(player, frame.getPreviousFrame());
        }
    }
}
