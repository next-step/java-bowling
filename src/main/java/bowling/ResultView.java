package bowling;

import java.util.List;

public class ResultView {

    private static final String NAME = "|%5s ";
    private static final String FRAME_RESULT = "| %-5s";
    private static final String FRAME_EMPTY = "|      ";
    private static final String SCORE_RESULT = "| %-5s";
    private static final String SCORE_EMPTY = "|      ";

    public static void printResult(UserName name, Frames frames) {
        printRoundTemplate();
        printUserName(name);
        printFrames(frames);
        printUserScore(frames);
    }

    private static void printRoundTemplate() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private static void printUserName(UserName name) {
        System.out.printf(NAME, name.getName());
    }


    private static void printFrames(Frames frames) {
        List<String> values = frames.getValues();

        for (String value : values) {
            System.out.printf(FRAME_RESULT, value);
        }

        int emptyFrame = FinalFrame.FINAL_FRAME_NUMBER + 1 - frames.size();
        for (int i = 0; i < emptyFrame; i++) {
            System.out.print(FRAME_EMPTY);
        }
        System.out.println();
    }

    private static void printUserScore(Frames frames) {
        System.out.print(SCORE_EMPTY);
        List<Integer> scores = frames.getScore();
        int totalScore = 0;

        for (Integer score : scores) {
            System.out.printf(SCORE_RESULT, totalScore += score);
        }

        int emptyFrame = FinalFrame.FINAL_FRAME_NUMBER + 1 - scores.size();
        for (int i = 0; i < emptyFrame; i++) {
            System.out.print(SCORE_EMPTY);
        }
        System.out.println();
    }
}
