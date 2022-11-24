package bowling;

import java.util.List;
import java.util.stream.Collectors;

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

        System.out.print(createUserScore(frames));
        int emptyFrame = FinalFrame.FINAL_FRAME_NUMBER + 1 - frames.size();
        for (int i = 0; i < emptyFrame; i++) {
            System.out.print(SCORE_EMPTY);
        }
        System.out.println();
    }

    private static String createUserScore(Frames frames) {
        List<Frame> values2 = frames.getValues2();

        String userScore = values2.stream().map(frame -> {
            if (frame.getScores() != Score.INCALCULABLE_SCORE) {
                return sumTotalScore(values2, values2.indexOf(frame) + 1);
            }
            return SCORE_EMPTY;
        }).collect(Collectors.joining());
        return userScore;
    }

    private static String sumTotalScore(List<Frame> frames, int limit) {
        int sum = frames.stream()
            .limit(limit)
            .mapToInt(Frame::getScores)
            .sum();
        return String.format(SCORE_RESULT, sum);
    }
}
