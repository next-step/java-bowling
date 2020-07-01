package bowling.view;

import bowling.domain.Frame;
import bowling.domain.ResultFrame;
import bowling.domain.Round;

public class OutputView {
    private static final String EMPTY = " |     ";

    public static void printSnapshot(final ResultFrame resultFrame) {
        printInfoLine();
        System.out.printf("| %s ", resultFrame.getGameUser());
        for (int i = 1; i <= resultFrame.count(); i++) {
            printFrame(resultFrame.getFrame(i));
        }
        for (int i = resultFrame.count() + 1; i <= 10; i++) {
            System.out.print(EMPTY);
        }
        System.out.println("|");
    }

    private static void printInfoLine() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private static void printFrame(final Frame frame) {
        System.out.print(" |  ");
        if (frame.isStrike()) {
            System.out.print(" X ");
            return;
        }
        if (!frame.isFinished()) {
            System.out.print(frame.getPinsOf(Round.FIRST_ROUND));
            System.out.print("  |  ");
            return;
        }
        if (frame.isSpare()) {
            System.out.print(frame.getPinsOf(Round.FIRST_ROUND) + "|/");
            return;
        }
        System.out.print(frame.getPinsOf(Round.FIRST_ROUND) + "|" + frame.getPinsOf(Round.SECOND_ROUND));
    }


}
