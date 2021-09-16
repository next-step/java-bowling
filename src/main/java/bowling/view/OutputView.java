package bowling.view;

import bowling.model.*;

import java.util.List;

public class OutputView {
    public static final int MAX_ROUND = 10;
    
    public static void printBowlingScore(String name, List<List<String>> scoreList) {
        printHeader();
        printAllScore(name, scoreList);
    }

    private static void printHeader() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("| NAME |");

        for (int i = 1; i < MAX_ROUND; i++) {
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
        for (int i = scoreList.size(); i < MAX_ROUND; i++) {
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

    public static String changeScore(int score, State bowlingResult) {
        if (bowlingResult instanceof Strike) {
            return "X";
        }

        if (bowlingResult instanceof Gutter) {
            return "-";
        }

        if (bowlingResult instanceof Spare) {
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
