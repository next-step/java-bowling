package bowling.step2.outputView;

import bowling.step2.domain.Frame;
import bowling.step2.domain.Lane;
import bowling.step2.outputView.shape.FrameResult;
import bowling.step2.outputView.shape.FrameResultFactory;

import java.util.List;

public class OutputView {
    public static void printNow(Lane lane) {
        List<Frame> scoreOfLane = lane.getScoreOfLane();
        printBoard();
        printName(lane);
        printResult(scoreOfLane);

    }

    private static void printBoard() {
        System.out.print(" NAME ");

        for (int i = 1; i <= 10; i++) {
            System.out.print("|    " + i + "    ");
        }

        System.out.println("|");
    }

    private static void printName(Lane lane) {
        System.out.print(" " + padRight(lane.nameOfLane(), 4) + " ");
    }

    private static void printResult(List<Frame> scoreOfLane) {
        for (int i = 1; i <= 10; i++) {
            printResultBody(scoreOfLane, i);
        }
        System.out.println();
    }

    private static void printResultBody(List<Frame> scoreOfLane, int index) {
        if (scoreOfLane.size() < index) {
            printMargin();
            return;
        }

        Frame frame = scoreOfLane.get(index - 1);
        FrameResult frameResult = FrameResultFactory.create(frame);

        frameResult.print();
    }

    private static void printMargin() {
        System.out.print("|         ");
    }

    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
}
