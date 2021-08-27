package bowling.step2.outputView;

import bowling.step2.domain.Count;
import bowling.step2.domain.Frame;
import bowling.step2.domain.Lane;
import bowling.step2.domain.LastFrame;

import java.util.List;

public class OutputView {
    public static void printNow(Lane lane) {
        List<Frame> scoreOfLane = lane.getScoreOfLane();
        System.out.print(" NAME ");

        for (int i = 1; i <= 10; i++) {
            System.out.print("|    " + i + "    ");
        }

        System.out.println();
        System.out.print(" " + padRight(lane.nameOfLane(), 4) + " ");

        for (int i = 1; i <= 10; i++) {
            if (scoreOfLane.size() < i) {
                System.out.print("|         ");
                continue;
            }
            Frame frame = scoreOfLane.get(i - 1);

            if (frame instanceof LastFrame) {
                System.out.print("|  " + frame.countOfFirst()
                        .value() + "|" + frame.countOfSecond()
                        .value() + "|" + ((LastFrame) frame).countOfAdditional()
                        .value());
                continue;
            }

            if (frame.countOfFirst() == Count.TEN) {
                System.out.print("|    X    ");
                continue;
            }

            if (frame.countOfFirst()
                    .sum(frame.countOfSecond()) == Count.TEN) {
                System.out.print("|   " + frame.countOfFirst()
                        .value() + "|/  ");
                continue;
            }

            System.out.print("|   " + frame.countOfFirst()
                    .value() + "|" + frame.countOfSecond()
                    .value() + "   ");
        }
        System.out.println();
    }

    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
}
