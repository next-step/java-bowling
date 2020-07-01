package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Round;

public class OutputView {

    public static void printFrame(final Frame frame) {
        if (frame.isStrike()) {
            System.out.print("X");
            return;
        }
        if (!frame.isFinished()) {
            System.out.print(frame.getPinsOf(Round.FIRST_ROUND));
            return;
        }
        if (frame.isSpare()) {
            System.out.print("|/");
            return;
        }
        System.out.print(frame.getPinsOf(Round.FIRST_ROUND) + "|" + frame.getPinsOf(Round.SECOND_ROUND));
    }
}
