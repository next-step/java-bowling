package bowling.view;

import bowling.domain.Bowling;
import bowling.domain.Frame;

public class ResultView {

    public static void printBowling(Bowling bowling) {
        System.out.println();
        int sum = 0;
        boolean isSecond = false;
        for (Frame frame : bowling.getFrames()) {
            isSecond = frame.isSecond();
            if (isSecond) {
                System.out.print(frame.getScore(sum));
                sum += frame.getCountOfHit();
            } else {
                System.out.print(frame.getScore(sum));
                sum = 0;
            }
        }

        for (int i = 0; i < 10 - bowling.getCurrentFrame(); i ++) {
            if (isSecond) {
                System.out.print("   |");
                isSecond = false;
            }
            System.out.print("     |");

        }
    }
}
