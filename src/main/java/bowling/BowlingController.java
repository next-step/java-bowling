package bowling;

import bowling.domain.*;
import bowling.score.Score;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {

    public static void main(String[] args) {
        String name = InputView.scanPlayer();
        Player player = Player.from(name);

        Frame frame = NormalFrame.first();
        for (int number = 1; number < Frame.LAST_FRAME; number++) {
            frame = processNormalFrame(frame, player);
        }
        processFinalFrame(frame, player);
    }

    private static void processFinalFrame(Frame frame, Player player) {
    }

    private static Frame processNormalFrame(Frame frame, Player player) {
        OutputView.printFrame(frame, player);
        Score score = Score.of(InputView.scanFirstBowl(frame));
        frame.bowl(score);

        if (frame.canBowl()) {
            score = Score.of(InputView.scanFirstBowl(frame));;
            frame.bowl(score);
        }
        return frame.next();
    }

}
