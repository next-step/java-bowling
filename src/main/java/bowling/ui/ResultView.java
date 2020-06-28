package bowling.ui;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.score.Score;

import java.util.List;
import java.util.stream.Stream;

public class ResultView {
    private static final String ROUND_SCORE_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String DEFAULT_SCORE_BOARD = "|  %s |      |      |      |      |      |      |      |      |      |      |";
    public static final int MAX_FRAME_SIZE = 10;

    public static void printDefaultScoreBoard(Player player) {
        System.out.println(ROUND_SCORE_BOARD);
        System.out.println(String.format(DEFAULT_SCORE_BOARD, player.toString()));
    }

    public static void printResult(Player player, Frames frames) {
        System.out.println(ROUND_SCORE_BOARD);
        printScoreLine(player, frames);
        printEmptyLine(frames);
        System.out.println("\n");
    }

    private static void printEmptyLine(Frames frames) {
        Stream.generate(() -> formatting(""))
                .limit(MAX_FRAME_SIZE - frames.size())
                .forEach(System.out::print);
    }

    private static void printScoreLine(Player player, Frames frames) {
        System.out.print("|" + formatting(player.toString()));
        frames.getFrames().stream()
                .map(frame -> {
                    List<Score> scores = frame.getScores();
                    String result = "";
                    for (Score score : scores) {
                        result += ScoreType.pointToScore(score.getScore());
                    }
                    return formatting(result);
                })
                .forEach(System.out::print);
    }

    private static String formatting(String input) {
        return String.format("  %-4s|", input);
    }
}
