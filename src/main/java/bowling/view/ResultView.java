package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import java.util.List;

public class ResultView {
    private static final String INNING_LINE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String NAME_LINE = "|  %-2s |      |      |      |      |      |      |      |      |      |      |\n";
    private static final String EMPTY_LINE = "|      |      |      |      |      |      |      |      |      |      |      |";

    public static void printPlayers(Players players) {
        System.out.println(INNING_LINE);
        for (Player player : players.getPlayers()) {
            System.out.printf(NAME_LINE, player.name());
            System.out.println(EMPTY_LINE);
        }
    }

    public static void printResult(Players players) {
        List<Player> playerList = players.getPlayers();

        for (Player player : playerList) {
            Frames frames = player.getFrames();
            Scores scores = player.getScores();
            System.out.println(INNING_LINE);
            printFrames(player, frames);
            printScores(scores);
        }
    }

    public static void printfinalScore(Players players) {
        System.out.println();
        System.out.println();
        for (Player player : players.getPlayers()) {
            int totalScore = player.getScores().totalScore();
            System.out.println(player.name() + "의 점수는 = " + totalScore);
        }
    }

    private static void printFrames(Player player, Frames frames) {
        System.out.print("|  " + player.name() + " |");
        for (Frame frame : frames.getFrames()) {
            System.out.print(frame.score());
        }
        System.out.println();
    }

    private static void printScores(Scores scores) {
        System.out.print("|      |");

        for (Score score : scores.getScores()) {
            if (score.isDone()) {
                System.out.print("  " + score.getScore() + "  |");
                continue;
            }
            System.out.print("      |");
        }
        System.out.println();
    }

}
