package bowling.view;

import bowling.domain.Bowling;
import bowling.domain.Frame;

import java.util.List;

public class ResultView {

    public static void printBowling(Bowling bowling, String name) {
        printBowlingFrameByOrder();
        printPlayerFrameByName(name);

        int previousScore = 0;
        boolean isSecond = false;

        for (Frame frame : bowling.getFrames()) {
            isSecond = frame.isRemain() && !frame.isStrike();
            previousScore = printFrameByNormal(frame, previousScore);
        }

        if (bowling.getFinalFrames().size() > 0) {
            printFinalFrame(bowling.getFinalFrames(), previousScore);
        } else {
            System.out.print(printEmptyFrame(isSecond));
        }

        if (bowling.getFinalFrames().size() == 2) {
            System.out.print(" |");
        }

        printEmptyFrameByNormalFrame(bowling.getCurrentFrame());
    }

    private static void printBowlingFrameByOrder() {
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
    
    private static int printFrameByNormal(Frame frame, int previousScore) {
        if (frame.isRemain() && !frame.isStrike()) {
            System.out.print("  "+frame.getScore(previousScore));
            return frame.getCountOfHit();
        }
        System.out.print(printScoreByFirst(frame, previousScore));
        return 0;
    }

    private static String printScoreByFirst(Frame frame, int previousScore) {
        if (frame.isStrike()) {
            return "  "+frame.getScore(previousScore)+"   |";
        }
        return "|"+frame.getScore(previousScore)+" |";
    }

    private static void printFinalFrame(List<Frame> frames, int previousScore) {
        System.out.print(" ");
        for (Frame frame : frames) {
            System.out.print(printFrameByFinalFrame(frame.getScore(previousScore), frames.size()));
            previousScore += frame.getCountOfHit();
        }
    }

    private static void printEmptyFrameByNormalFrame(long currentFrame) {
        for (int i = 0; i < 9 - currentFrame; i ++) {
            System.out.print("      |");
        }
        System.out.println();
    }

    private static String printFrameByFinalFrame(String score, int size) {
        if (size == 1) {
            return score + "    |";
        }
        return score +"|";
    }

    private static String printEmptyFrame(boolean isValue) {
        if (isValue) {
            return "   |";
        }
        return "      |";
    }
}
