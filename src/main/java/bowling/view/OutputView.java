package bowling.view;

import bowling.domain.*;

import java.util.List;

public class OutputView {

    public static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    public static final String EMPTY_FRAME = "      |";
    public static final String GUTTER = "-";
    public static final String STRIKE = "X";

    public static void printResult(List<Player> players) {
        System.out.println(HEADER);
        for (Player player : players) {
            printFrames(player.getName(), player.getFrames());
            printScores(player.getScores());
        }
        System.out.println();
    }

    private static void printFrames(Name name, Frames frames) {
        System.out.printf("|  %s |", name.getName());
        for (int i = 0; i < 10; i++) {
            System.out.print(makeFrameResult(frames.getFrames(), i));
        }
        System.out.println();
    }

    private static void printScores(Scores scores) {
        System.out.print("|      |");
        for (int i = 0; i < 10; i++) {
            System.out.print(makeScores(scores.getScores(), i));
        }
        System.out.println();
    }

    private static String makeScores(List<Score> scores, int index) {
        if (scores.size() > index && scores.get(index).isEnd()) {
            return String.format("  %3d |", scores.get(index).getScore());
        }
        return EMPTY_FRAME;
    }

    private static String makeFrameResult(List<Frame> frames, int index) {
        if (frames.size() > index) {
            return makePinCount(frames.get(index).getScores());
        }
        return EMPTY_FRAME;
    }

    private static String makePinCount(Rolls rolls) {
        if (rolls.size() == 3) {
            return String.format(" %s|%s|", getSpareOrMiss(rolls), getScoreOrGutterOrStrike(rolls, 2));
        }
        if (rolls.size() == 2) {
            return String.format("  %s |", getSpareOrMiss(rolls));
        }
        if (rolls.size() == 1) {
            return getStrikeOrProgress(rolls);
        }
        return EMPTY_FRAME;
    }

    private static String getStrikeOrProgress(Rolls rolls) {
        if (rolls.getScores().get(0).getScore() == 10) {
            return "  X   |";
        }
        return String.format("  %s|  |", getScoreOrGutterOrStrike(rolls, 0));
    }

    private static String getSpareOrMiss(Rolls rolls) {
        if (rolls.sum() == 10) {
            return String.format("%s|/", getScoreOrGutterOrStrike(rolls, 0));
        }
        return String.format("%s|%s", getScoreOrGutterOrStrike(rolls, 0), getScoreOrGutterOrStrike(rolls, 1));
    }

    private static String getScoreOrGutterOrStrike(Rolls rolls, int index) {
        int score = rolls.getScores().get(index).getScore();
        if (score == 0) {
            return GUTTER;
        }
        if (score == 10) {
            return STRIKE;
        }
        return String.valueOf(score);
    }
}
