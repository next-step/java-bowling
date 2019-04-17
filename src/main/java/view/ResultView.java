package view;

import domain.game.BowlingGame;
import util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.frame.Frames.LAST_FRAME;
import static domain.frame.Frames.START_FRAME;

public class ResultView {
    public static void showResult(BowlingGame game) {
        showHeadLine();
        showStatus(game);
        showScore(game);
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
    }

    private static String getStatus(BowlingGame game) {
        return getNameStatusColumn(game) + getFrameStatusColumns(game) + "|";
    }

    private static String getNameStatusColumn(BowlingGame game) {
        return String.format("|  %3s |", game.getPlayerName());
    }

    private static String getFrameStatusColumns(BowlingGame game) {
        return getCollectedColumns(game.getStatusStrings());
    }

    private static void showScore(BowlingGame game) {
        System.out.println(getScore(game));
        System.out.println();
    }

    private static String getScore(BowlingGame game) {
        String scores = getCollectedColumns(getFrameScoreColumns(game));
        return padBlankColumnAndGet(scores);
    }

    private static List<String> getFrameScoreColumns(BowlingGame game) {
        return game.getScoreStrings();
    }

    private static String getCollectedColumns(List<String> columns) {
        return columns.stream()
                .map(column -> StringUtils.center(column, 6))
                .collect(Collectors.joining("|"));
    }

    private static String padBlankColumnAndGet(String string) {
        return getBlankColumn() + string + "|";
    }

    private static String getBlankColumn() {
        return "|      |";
    }
}