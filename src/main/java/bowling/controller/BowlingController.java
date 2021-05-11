package bowling.domain.controller;


import bowling.domain.Player;
import bowling.domain.frame.*;
import bowling.domain.view.InputView;
import bowling.domain.view.ResultView;


public class BowlingController {

    public static void start(Player player) {
        Frame frame = new NormalFrame(Round.createOf(), null);
        ResultView.printRound();
        ResultView.printResult(player.getName());

        while (!frame.isEnd()) {
            int frameRoundCount = getFrameRoundCount(frame);
            frame = frame.pitch(InputView.getPitch(frameRoundCount));

            ResultView.printBowlingResult(player, frame.getFrameResponse());
        }
    }

    private static int getFrameRoundCount(Frame frame) {
        Round round = frame.getRound();

        if (frame instanceof FinalFrame) {
            return 10;
        }

        return round.getRound();
    }
}
