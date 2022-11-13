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

    private static String makeScore(Rolls rolls) {
        if (rolls.size() == 3) {
            return String.format(" %s|%s|", getStrikeOrMiss(rolls), getScoreOrGutter(rolls, 2));
        }
        if (rolls.size() == 2) {
            return String.format("  %s |", getStrikeOrMiss(rolls));
        }
        if (rolls.size() == 1) {
            if (rolls.getScores().get(0).getScore() == 10) {
                return "  X   |";
            }
        }
        return String.format("  %s|  |", getScoreOrGutter(rolls, 0));
    }

    private static String getStrikeOrMiss(Rolls rolls) {
        if (rolls.sum().equals(new Score(10))) {
            return String.format("%s|/", getScoreOrGutter(rolls, 0));
        }
        return String.format("%s|%s", getScoreOrGutter(rolls, 0), getScoreOrGutter(rolls, 1));
    }

    private static String getScoreOrGutter(Rolls rolls, int index) {
        int score = rolls.getScores().get(index).getScore();
        if (score == 0) {
            return "-";
        }
        return String.valueOf(score);
    }
}
