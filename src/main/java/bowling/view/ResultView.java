package bowling.view;

import bowling.domain.Bowling;
import bowling.domain.Frame;

public class ResultView {

    public static void printBowling(Bowling bowling, String name) {
        printBowlingFrameByOrder();
        printPlayerFrameByName(name);
        int sum = 0;
        boolean isSecond = false;
        for (Frame frame : bowling.getFrames()) {
            isSecond = frame.isRemain() && !frame.isStrike();
            if (isSecond) {
                System.out.print("  "+frame.getScore(sum));
                sum += frame.getCountOfHit();
            } else {
                if (frame.isStrike()) {
                    System.out.print("  "+frame.getScore(sum)+"   |");
                } else {
                    System.out.print("|"+frame.getScore(sum)+" |");
                }
                sum = 0;
            }
        }

        int size = bowling.getFinalFrames().size();
        System.out.print(" ");
        for (Frame frame : bowling.getFinalFrames()) {
            System.out.print(frame.getScore(sum)+"|");
            sum += frame.getCountOfHit();
        }
        if (size == 2) {
            System.out.println(" |");
        }


        for (int i = 0; i < 9 - bowling.getCurrentFrame(); i ++) {
            if (isSecond) {
                System.out.print("   |");
                isSecond = false;
            }
            System.out.print("      |");
        }
        System.out.println();
    }

    public static void printBowlingFrameByOrder() {
        System.out.print("| NAME |");
        for (int i = 1; i < 11; i++) {
            if (i < 10) {
                System.out.print("  0"+i+"  |");
            } else {
                System.out.print("  "+i+"  |");
            }
        }
        System.out.println();
    }

    public static void printPlayerFrameByName(String name) {
        System.out.print("|  "+name+" |");
    }

}
