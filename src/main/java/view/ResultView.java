package view;

import java.util.ArrayList;
import java.util.List;

public class ResultView {
    private static String INITIAL_LINE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static String SCORE_LINE_FORMAT = "|  %s |%s";
    private static String DELIMITER = "|";
    private static String SINGLE_SCORE = "  %s   ";
    private static String DOUBLE_SCORE = "  %s|%s ";
    private static String TRIPLE_SCORE = " %s|%s|%s";

    public static void printInitialScoreBoard(String name) {
        printInitialLine();
        printScoreLine(name, new ArrayList<>());
    }

    public static void printScoreBoard(String name, List<List<Integer>> scoresByFrame) {
        printInitialLine();
        printScoreLine(name, scoresByFrame);
    }

    private static void printInitialLine() {
        System.out.println(INITIAL_LINE);
    }

    private static void printScoreLine(String name, List<List<Integer>> scores) {
        List<String> scoresByFrameString = new ArrayList<>();
        for (List<Integer> scoresByFrame : scores) {
            String scoresByFrameToString = "";
            if (scoresByFrame.size() == 1) {
                scoresByFrameToString = String.format(SINGLE_SCORE, scoreToString(scoresByFrame.get(0)));
            } else if (scoresByFrame.size() == 2) {
                if (scoresByFrame.get(0) + scoresByFrame.get(1) == 10) {
                    scoresByFrameToString =
                            String.format(DOUBLE_SCORE,
                                    scoreToString(scoresByFrame.get(0)), "/");
                } else {
                    scoresByFrameToString =
                            String.format(DOUBLE_SCORE,
                                    scoreToString(scoresByFrame.get(0)), scoreToString(scoresByFrame.get(1)));
                }
            } else {
                scoresByFrameToString =
                        String.format(TRIPLE_SCORE,
                                scoreToString(scoresByFrame.get(0)), "/", scoreToString(scoresByFrame.get(2)));
            }
            scoresByFrameString.add(scoresByFrameToString);
        }
        for (int index = scoresByFrameString.size(); index <= 10; index++) {
            scoresByFrameString.add("      ");
        }
        System.out.println(String.format(SCORE_LINE_FORMAT, name, scoresToString(scoresByFrameString)));
    }

    private static String scoreToString(int num) {
        if (num == 0) {
            return "-";
        } else if (num == 10) {
            return "X";
        } else {
            return String.valueOf(num);
        }
    }

    private static String scoresToString(List<String> scoresByFrameString) {
        return scoresByFrameString.stream().reduce((o1, o2) -> o1 + DELIMITER + o2).orElse("");
    }
}
