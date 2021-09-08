package step2.view;

import java.util.List;

public class ResultView {

    private static final String OUTPUT_FRAME_FORMAT = "  %02d  |";
    private static final String OUTPUT_SCORE_FORMAT = "  %-3s |";
    private static final int FRAME_NUMBER_START = 1;
    private static final int FRAME_NUMBER_END = 10;

    public static void printFramesResult(List<String> framesResult) {
        printFrameResult(framesResult);
    }

    public static void printFramesColumnName() {
        System.out.print("| NAME |");
        for (int i = FRAME_NUMBER_START; i <= FRAME_NUMBER_END; i++) {
            System.out.printf(OUTPUT_FRAME_FORMAT, i);
        }
        System.out.println();
    }

    public static void printUserName(String userName) {
        System.out.printf("|  %3s |", userName);
    }

    private static void printFrameResult(List<String> framesResult) {
        String frameResult = String.join("|", framesResult);
        System.out.printf(OUTPUT_SCORE_FORMAT, frameResult);
    }

    public static void printEmptyResult(int frameRound) {
        for (int i = frameRound; i < FRAME_NUMBER_END; i++) {
            System.out.printf(OUTPUT_SCORE_FORMAT, "");
        }
        System.out.println();
    }
}
