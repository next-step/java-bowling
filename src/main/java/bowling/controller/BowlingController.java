package bowling.controller;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {

    public static void runBowlingGame() {
        Player player = InputView.inputPlayerName();

        BowlingGameResult bowlingGameResult = BowlingGameResult.of(player, Frames.init());

        OutputView.printResult(player, bowlingGameResult);


        Frame frame = BasicFrame.of(BasicScores.init(), null);
        while (frame instanceof BasicFrame) {
            frame = initBasicFrame(bowlingGameResult, frame);
        }
        initLastFrame(bowlingGameResult, frame);


    }

    private static void initLastFrame(BowlingGameResult bowlingGameResult, Frame frame) {

    }

    private static Frame initBasicFrame(BowlingGameResult bowlingGameResult, Frame frame) {
        return null;
    }

}
