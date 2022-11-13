package bowling.view;

import bowling.domain.*;

import java.util.List;

public class OutputView {
    public static void printScores(Player player) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.printf("|  %s |", player.getName().getName());

        Frames frames = player.getFrames();
        for (int i = 0; i < 10; i++) {
            System.out.print(makeFrame(frames.getFrames(), i));
        }
        System.out.println();
    }

    private static String makeFrame(List<Frame> frames, int index) {
        if (frames.size() > index) {
            return makeScore(frames.get(index).getScores());
        }
        return "      |";
    }

    private static String makeScore(Scores scores) {
        if (scores.size() == 3) {
            return String.format(" %s|%s|", getStrikeOrMiss(scores), getScoreOrGutter(scores, 2));
        }
        if (scores.size() == 2) {
            return String.format("  %s |", getStrikeOrMiss(scores));
        }
        if (scores.size() == 1) {
            if (scores.getScores().get(0).getScore() == 10) {
                return "  X   |";
            }
        }
        return String.format("  %s|  |", getScoreOrGutter(scores, 0));
    }

    private static String getStrikeOrMiss(Scores scores) {
        if (scores.sum().equals(new Score(10))) {
            return String.format("%s|/", getScoreOrGutter(scores, 0));
        }
        return String.format("%s|%s", getScoreOrGutter(scores, 0), getScoreOrGutter(scores, 1));
    }

    private static String getScoreOrGutter(Scores scores, int index) {
        int score = scores.getScores().get(index).getScore();
        if (score == 0) {
            return "-";
        }
        return String.valueOf(score);
    }
}
