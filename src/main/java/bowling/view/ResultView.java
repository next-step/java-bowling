package bowling.view;

import bowling.domain.Bowling;
import bowling.domain.Frame;

public class ResultView {

    public static void printBowling(Bowling bowling) {
        int sum = 0;
        for (Frame frame : bowling.getFrames()) {
            if (frame.isSecond()) {
                System.out.print(frame.getScore(sum) + "|");
                sum += frame.getCountOfHit();
            } else {
                System.out.println(frame.getScore(sum));
                sum = 0;
            }
        }
    }
}
