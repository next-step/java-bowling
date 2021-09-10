package bowling;

import java.util.List;

import static bowling.controller.BowlingGame.DEFAULT_ROUND_COUNT;

public class OutputView {
    public static void printBowlingScore(String name, List<List<String>> scoreList) {
        printHeader();
        printAllScore(name, scoreList);
    }

    private static void printHeader() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("| NAME |");

        for (int i = 1; i < DEFAULT_ROUND_COUNT; i++) {
            stringBuilder.append("  0" + i + "  |");
        }

        stringBuilder.append("  10  |");

        System.out.println(stringBuilder.toString());
    }

    private static void printAllScore(String name, List<List<String>> scoreList) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("|  " + name + " |");

        for (List<String> roundScore : scoreList) {
            String formatScore = formatRoundScore(roundScore);
            stringBuilder.append(formatScore);
        }

        stringBuilder.append(formatEmptyScore(scoreList));

        System.out.println(stringBuilder.toString());
    }

    private static String formatEmptyScore(List<List<String>> scoreList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = scoreList.size(); i < DEFAULT_ROUND_COUNT; i++) {
            stringBuilder.append("      |");
        }

        return stringBuilder.toString();
    }

    private static String formatRoundScore(List<String> roundScore) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String point : roundScore) {
            stringBuilder.append("|" + point);
        }

        String result = stringBuilder.toString();
        result = result.substring(1);

        return addBlank(result);
    }

    public static String changeScore(int score, BowlingResult bowlingResult) {
        if (bowlingResult == BowlingResult.STRIKE) {
            return "X";
        }

        if (bowlingResult == BowlingResult.GUTTER) {
            return "-";
        }

        if (bowlingResult == BowlingResult.SPARE) {
            return "/";
        }

        return String.valueOf(score);
    }

    private static String addBlank(String score) {
        int length = score.length();

        if (length == 1) {
            return "  " + score + "   |";
        }

        if (length == 5) {
            return " " + score + "|";
        }

        return "  " + score + " |";
    }
}
