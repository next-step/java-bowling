package view;

import game.BowlingGame;
import game.Frame;

import java.util.ArrayList;
import java.util.List;

public class ResultView {
    private static String INITIAL_LINE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static String SCORE_LINE_FORMAT = "|  %s |%s";
    private static String DELIMITER = "|";
    private static String SINGLE_SCORE = "  %s   ";
    private static String DOUBLE_SCORE = "  %s|%s ";
    private static String TRIPLE_SCORE = " %s|%s|%s";

    public static void printInitialScoreBoard(BowlingGame bowlingGame) {
        printInitialLine();
        printScoreLine(bowlingGame);
    }

    public static void printScoreBoard(BowlingGame bowlingGame) {
        printInitialLine();
        printScoreLine(bowlingGame);
    }

    private static void printInitialLine() {
        System.out.println(INITIAL_LINE);
    }

    private static void printScoreLine(BowlingGame bowlingGame) {
        List<String> scoresByFrameString = new ArrayList<>();
        for (Frame frame : bowlingGame.getFrames()) {
            String scoresByFrameToString = "";
            if (frame.getBonus() != null) {
                if (frame.getScores().size() == 1) {
                    scoresByFrameToString =
                            String.format(DOUBLE_SCORE,
                                    scoreToString(frame.getScores().get(0)), scoreToString(frame.getBonus()));
                } else {
                    scoresByFrameToString =
                            String.format(TRIPLE_SCORE,
                                    scoreToString(frame.getScores().get(0)), "/", scoreToString(frame.getBonus()));
                }
            } else if (frame.getScores().size() == 1) {
                scoresByFrameToString = String.format(SINGLE_SCORE, scoreToString(frame.getScores().get(0)));
            } else {
                if (frame.getScores().get(0) + frame.getScores().get(1) == 10) {
                    scoresByFrameToString =
                            String.format(DOUBLE_SCORE,
                                    scoreToString(frame.getScores().get(0)), "/");
                } else {
                    scoresByFrameToString =
                            String.format(DOUBLE_SCORE,
                                    scoreToString(frame.getScores().get(0)), scoreToString(frame.getScores().get(1)));
                }
            }
            scoresByFrameString.add(scoresByFrameToString);
        }
        for (int index = scoresByFrameString.size(); index <= 10; index++) {
            scoresByFrameString.add("      ");
        }
        System.out.println(String.format(SCORE_LINE_FORMAT, bowlingGame.getName(), scoresToString(scoresByFrameString)));
        System.out.println();
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
