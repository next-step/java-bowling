package bowling.view;

import java.util.List;

import bowling.model.frame.Board;

public class OutputView {
    public static final int MAX_ROUND = 10;

    public static void printResult(String name, Board board) {
        printHeader();
        printState(name, board);
        printScore(board);
    }

    private static void printScore(Board board) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("|      |");

        List<Integer> scoreResult = board.getScoreResult();
        for (int score : scoreResult) {
            String result = addBlank(String.valueOf(score));
            stringBuilder.append(result);
        }

        stringBuilder.append(formatEmptyScore(scoreResult.size()));

        System.out.println(stringBuilder.toString());
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

    private static void printState(String name, Board board) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("|  " + name + " |");

        List<String> stateResult = board.getStateResult();
        for (String state : stateResult) {
            String result = addBlank(state);
            stringBuilder.append(result);
        }

        stringBuilder.append(formatEmptyScore(stateResult.size()));

        System.out.println(stringBuilder.toString());
    }

    private static String formatEmptyScore(int stateSize) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < MAX_ROUND - stateSize; i++) {
            stringBuilder.append("      |");
        }

        return stringBuilder.toString();
    }

    private static String addBlank(String score) {
        int length = score.length();

        if (length == 1) {
            return "  " + score + "   |";
        }

        if (length == 2) {
            return "  " + score + "  |";
        }

        if (length == 5) {
            return " " + score + "|";
        }

        return "  " + score + " |";
    }
}
