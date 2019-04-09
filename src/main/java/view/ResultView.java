package view;

import domain.BowlingGame;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.frame.Frames.LAST_FRAME;
import static domain.frame.Frames.START_FRAME;

public class ResultView {
    public static void showResult(BowlingGame game) {
        showHeadLine();
        showStatus(game);
    }

    private static void showHeadLine() {
        System.out.println(getHeadLine());
    }

    private static String getHeadLine() {
        return getNameHeadColumn() + getFrameHeadColumns() + "|";
    }

    private static String getNameHeadColumn() {
        return "| NAME |";
    }

    private static String getFrameHeadColumns() {
        return IntStream.rangeClosed(START_FRAME, LAST_FRAME)
                .mapToObj(number -> String.format("  %02d  ", number))
                .collect(Collectors.joining("|"));
    }

    private static void showStatus(BowlingGame game) {
        System.out.println(getStatus(game));
        System.out.println();
    }

    private static String getStatus(BowlingGame game) {
        return getNameStatusColumn(game) + getFrameStatusColumn(game) + "|";
    }

    private static String getNameStatusColumn(BowlingGame game) {
        return String.format("|  %3s |", game.getPlayerName());
    }

    private static String getFrameStatusColumn(BowlingGame game) {
        return game.getFramesStatus();
    }
}