package bowling.view;

import bowling.model.State;

import static bowling.controller.Main.scoreResult;
import static bowling.controller.Main.stateResult;

public class OutputView {
    public static final int MAX_ROUND = 10;

    public static void printResult(String name) {
        printHeader();
        printState(name);
        printScore();
    }

    private static void printScore() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("|      |");

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

    private static void printState(String name) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("|  " + name + " |");

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

    public static String changeScore(State state) {
        return state.toString();
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
