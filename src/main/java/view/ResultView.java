package view;

import game.*;

import java.util.ArrayList;
import java.util.List;

public class ResultView {
    private static String INITIAL_LINE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static String SCORE_LINE_FORMAT = "|  %s |%s";
    private static String DELIMITER = "|";
    private static String FIRST_ROLLING_SCORE = "  %s   ";
    private static String SECOND_ROLLING_SCORE = "  %s|%s ";
    private static String BONUS_ROLLING_SCORE = " %s|%s|%s";
    private static String GAME_TO_GO_FORMAT = "      ";

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
            scoresByFrameString.add(scoreToString(frame));
        }
        System.out.println(String.format(SCORE_LINE_FORMAT, bowlingGame.getName(), gameScoreToString(scoresByFrameString)));
        System.out.println();
    }

    private static String scoreToString(Frame frame) {
        List<Score> scores = frame.getScores();
        if (frame.getFrameType() == FrameType.NORMAL || frame.getBonus() == null) {
            if (scores.size() == 1) {
                return String.format(FIRST_ROLLING_SCORE,
                        firstRollingScore(frame));
            } else {
                return String.format(SECOND_ROLLING_SCORE,
                        firstRollingScore(frame),
                        secondRollingScoreToString(frame));
            }
        }
        return String.format(BONUS_ROLLING_SCORE,
                firstRollingScore(frame),
                secondRollingScoreToString(frame),
                bonusRollingScore(frame.getBonus()));
    }

    private static String bonusRollingScore(Score bonus) {
        return ScoreType.toString(bonus.getScore());
    }

    private static String secondRollingScoreToString(Frame frame) {
        if (frame.getGameType() == GameType.SPARE) {
            return "/";
        }
        return ScoreType.toString(frame.getScores().get(1).getScore());
    }

    private static String firstRollingScore(Frame frame) {
        return ScoreType.toString(frame.getScores().get(1).getScore());
    }

    private static String gameScoreToString(List<String> scoresByFrameString) {
        for (int index = scoresByFrameString.size(); index <= 10; index++) {
            scoresByFrameString.add(GAME_TO_GO_FORMAT);
        }
        return scoresByFrameString.stream().reduce((o1, o2) -> o1 + DELIMITER + o2).orElse("");
    }
}
