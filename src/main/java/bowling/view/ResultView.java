package bowling.view;

import bowling.domain.Bowling;
import bowling.domain.Frame;

import java.util.List;

public class ResultView {

    private static final int PREVIOUS_VALUE = 0;
    private static final int MIN_SIZE = 0;
    private static final String DEFAULT_EMPTY_FRAME_BOUNDARY = "      |";
    private static final String EXIST_VALUE_FRAME_BOUNDARY = "   |";
    private static final String FRAME_RIGHT_BOUNDARY = " |";
    private static final String BOUNDARY = "|";

    public static void printBowling(Bowling bowling, String name) {

        printBowlingFrameByOrder();
        printPlayerFrameByName(name);

        int previousScore = PREVIOUS_VALUE;
        boolean isSecond = false;

        for (Frame frame : bowling.getFrames()) {
            isSecond = frame.isRemain() && !frame.isStrike();
            previousScore = printFrameByNormal(frame, previousScore);
        }

        if (bowling.getFinalFrames().size() > MIN_SIZE) {
            printFinalFrame(bowling.getFinalFrames(), previousScore);
        } else {
            System.out.print(printEmptyFrame(isSecond));
        }

        if (bowling.getFinalFrames().size() == 2) {
            System.out.print(FRAME_RIGHT_BOUNDARY);
        }

        printEmptyFrameByNormalFrame(bowling.getCurrentFrame());
    }

    private static void printPlayerFrameByName(String name) {
        System.out.print("|  " + name + FRAME_RIGHT_BOUNDARY);
    }

    private static void printBowlingFrameByOrder() {
        System.out.print("| NAME |");
        for (int i = 1; i < 11; i++) {
            if (i < 10) {
                System.out.print("  0" + i + "  |");
            } else {
                System.out.print("  " + i + "  |");
            }
        }
        System.out.println();
    }

    private static int printFrameByNormal(Frame frame, int previousScore) {
        if (frame.isRemain() && !frame.isStrike()) {
            System.out.print("  " + frame.getScoreByNormalFrame(previousScore));
            return frame.getCountOfHit();
        }
        System.out.print(printScoreByFirst(frame, previousScore));
        return PREVIOUS_VALUE;
    }

    private static String printScoreByFirst(Frame frame, int previousScore) {
        if (frame.isStrike()) {
            return "  " + frame.getScoreByNormalFrame(previousScore) + EXIST_VALUE_FRAME_BOUNDARY;
        }
        return BOUNDARY + frame.getScoreByNormalFrame(previousScore) + FRAME_RIGHT_BOUNDARY;
    }

    private static void printEmptyFrameByNormalFrame(long currentFrame) {
        for (int i = 0; i < 9 - currentFrame; i++) {
            System.out.print(DEFAULT_EMPTY_FRAME_BOUNDARY);
        }
        System.out.println();
    }

    private static void printFinalFrame(List<Frame> frames, int previousScore) {
        System.out.print(" ");
        for (Frame frame : frames) {
            System.out.print(printFrameByFinalFrame(frame.getScoreByFinalFrame(previousScore), frames.size()));
            previousScore += frame.getCountOfHit();
        }
    }

    private static String printFrameByFinalFrame(String score, int size) {
        if (size == 1) {
            return score + "    |";
        }
        return score + BOUNDARY;
    }

    private static String printEmptyFrame(boolean isValue) {
        if (isValue) {
            return EXIST_VALUE_FRAME_BOUNDARY;
        }
        return DEFAULT_EMPTY_FRAME_BOUNDARY;
    }
}
