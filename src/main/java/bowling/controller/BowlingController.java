package bowling.controller;

import bowling.domain.Player;
import bowling.domain.frame.Frames;
import bowling.domain.game.Bowling;
import bowling.domain.point.Point;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {

    public static void runBowlingGame() {
        Player player = InputView.inputPlayerName();

        Bowling bowling = Bowling.of(player, Frames.init());

        while (!bowling.isGameFinished()) {
            int frameNumber = bowling.getCurrentFrameNumber();
            Point pointPitch = InputView.inputPitchBowl(frameNumber);

            bowling.pitch(pointPitch);
            OutputView.printResult(bowling);
        }
    }


}
