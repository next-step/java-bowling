package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.score.ScoreResult;

import java.util.List;
import java.util.stream.IntStream;

public class ResultView {

    private static final ScoreResult scoreResult = ScoreResult.of();

    private ResultView() {
    }

    public static void printBoard(Player player, Frames frames) {
        printBoardHead();
        printBoardFrame(player, frames);
        printBoardScore();
    }

    private static void printBoardHead() {
        System.out.printf("| NAME |");
        IntStream.rangeClosed(1, 10)
                .forEach(i -> System.out.printf("   " + String.format("%02d", i) + "   |"));
        System.out.println();
    }

    private static void printBoardFrame(Player player, Frames frames) {
        scoreResult.clear();
        List<Frame> frameList = frames.getFrames();
        System.out.printf("|  " + player.getName() + " |");

        frameList.forEach(f -> {
            scoreResult.addScoreResult(f.getScoreResult());
            System.out.printf("   " + String.format("%-3s", f.getState()) + String.format("%3s", "|"));
        });

        emptyBoard(frameList.size());
    }

    private static void printBoardScore() {
        List<Integer> scores = scoreResult.getScores();
        System.out.printf("|      |");

        scores.forEach(s -> System.out.printf("   " + String.format("%-3s", s) + String.format("%3s", "|")));

        emptyBoard(scores.size());
    }

    private static void emptyBoard(int start) {
        IntStream.range(start, 10)
                .forEach(i -> System.out.printf("        |"));
        System.out.println();
    }
}
