package bowling;

import bowling.bowler.Bowler;
import bowling.frame.BowlingBoard;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {
        Bowler bowler = Bowler.of(InputView.inputPlayerName());

        BowlingBoard bowling = BowlingBoard.start(bowler);
        ResultView.printGameBoard(bowling);

        while (!bowling.isFinished()) {
            int frameNumber = bowling.getFrameNumber();
            bowling.bowl(InputView.inputPins(frameNumber));

            ResultView.printGameBoard(bowling);
        }
    }

}
