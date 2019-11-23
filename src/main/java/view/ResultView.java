package view;

import bowling.BowlingGame;
import bowling.frame.Frame;
import bowling.frame.FrameScoreType;
import bowling.score.BonusScore;
import bowling.score.rollling.Pin;
import bowling.score.rollling.Rolling;

import java.util.ArrayList;
import java.util.List;

import static bowling.Frames.FRAME_COUNT;

public class ResultView {
    private static final String INITIAL_LINE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String SCORE_LINE_FORMAT = "|  %s |%s";
    private static final String TOTAL_SCORE_LINE_FORMAT = "|      |%s";
    private static final String DELIMITER = "|";
    private static final String FIRST_ROLLING_SCORE = "  %s   ";
    private static final String SECOND_ROLLING_SCORE = "  %s|%s ";
    private static final String THIRD_ROLLING_SCORE = " %s|%s|%s";
    private static final String TOTAL_SCORE_DOUBLE_DIGIT = "%4d ";
    private static final String GAME_TO_GO_FORMAT = "      ";

    public static void printInitialScoreBoard(BowlingGame bowlingGame) {
        printInitialLine();
        printScoreLine(bowlingGame);
        printTotalScoreLine(bowlingGame);
    }

    public static void printScoreBoard(BowlingGame bowlingGame) {
        printInitialLine();
        printScoreLine(bowlingGame);
        printTotalScoreLine(bowlingGame);
    }

    private static void printInitialLine() {
        System.out.println(INITIAL_LINE);
    }

    private static void printScoreLine(BowlingGame bowlingGame) {
        List<String> scoresByFrameString = new ArrayList<>();
        for (Frame frame : bowlingGame.getFrames().getFrames()) {
            scoresByFrameString.add(scoreToString(frame));
        }
        System.out.println(String.format(SCORE_LINE_FORMAT,
                bowlingGame.getUser(), gameScoreToString(scoresByFrameString)));
    }

    private static void printTotalScoreLine(BowlingGame bowlingGame) {
        List<String> totalScoresByFrameString = new ArrayList<>();
        int sum = 0;
        for (Frame frame : bowlingGame.getFrames().getFrames()) {
            int nextScore = frame.getScoreSum();
            if (nextScore < 0) {
                break;
            }
            sum += nextScore;
            totalScoresByFrameString.add(sumToString(sum));
        }
        System.out.println(String.format(TOTAL_SCORE_LINE_FORMAT,
                gameScoreToString(totalScoresByFrameString)));
        System.out.println();
    }

    private static String sumToString(int sum) {
        return String.format(TOTAL_SCORE_DOUBLE_DIGIT, sum);
    }

    private static String scoreToString(Frame frame) {
        if (frame.getBonus() == null) {
            return basicGameScoreToString(frame);
        }
        return bonusGameScoreToString(frame);
    }

    private static String bonusGameScoreToString(Frame frame) {
        BonusScore bonusScore = frame.getBonus();
        if (frame.getGameType() == FrameScoreType.SPARE) {
            return String.format(THIRD_ROLLING_SCORE,
                    firstRollingScore(frame),
                    secondRollingScoreToString(frame),
                    bonusRollingScore(bonusScore.getRollings().get(0)));
        }

        if (bonusScore.getRollings().size() == 1) {
            return String.format(SECOND_ROLLING_SCORE,
                    firstRollingScore(frame),
                    bonusRollingScore(bonusScore.getRollings().get(0)));
        }

        return String.format(THIRD_ROLLING_SCORE,
                firstRollingScore(frame),
                bonusRollingScore(bonusScore.getRollings().get(0)),
                bonusRollingScore(bonusScore.getRollings().get(1)));
    }

    private static String basicGameScoreToString(Frame frame) {
        List<Rolling> rollings = frame.getScores();
        if (rollings.size() == 1) {
            return String.format(FIRST_ROLLING_SCORE,
                    firstRollingScore(frame));
        }
        return String.format(SECOND_ROLLING_SCORE,
                firstRollingScore(frame),
                secondRollingScoreToString(frame));
    }

    private static String bonusRollingScore(Rolling bonus) {
        return Pin.toString(bonus.getScore());
    }

    private static String secondRollingScoreToString(Frame frame) {
        if (frame.getGameType() == FrameScoreType.SPARE) {
            return "/";
        }
        return Pin.toString(frame.getScores().get(1).getScore());
    }

    private static String firstRollingScore(Frame frame) {
        return Pin.toString(frame.getScores().get(0).getScore());
    }

    private static String gameScoreToString(List<String> scoresByFrameString) {
        for (int index = scoresByFrameString.size(); index <= FRAME_COUNT; index++) {
            scoresByFrameString.add(GAME_TO_GO_FORMAT);
        }
        return scoresByFrameString.stream().reduce((o1, o2) -> o1 + DELIMITER + o2).orElse(null);
    }
}