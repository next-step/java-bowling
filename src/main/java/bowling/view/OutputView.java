package bowling.view;

import java.util.LinkedList;
import java.util.List;

import bowling.model.*;

public class OutputView {
    public static final int MAX_ROUND = 10;

    public static void printResult(String name, List<String> states, List<Integer> scoreResult) {
        printHeader();
        printState(name, states);
        printScore(scoreResult);
    }

    private static void printScore(List<Integer> scoreResult) {
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

    private static void printState(String name, List<String> states) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("|  " + name + " |");

        for (String state : states) {
            String result = addBlank(state);
            stringBuilder.append(result);
        }

        stringBuilder.append(formatEmptyScore(states.size()));

        System.out.println(stringBuilder.toString());
    }

    private static String formatEmptyScore(int stateSize) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < MAX_ROUND - stateSize; i++) {
            stringBuilder.append("      |");
        }

        return stringBuilder.toString();
    }

    public static String changeScore(LinkedList<String> stateResult, State state, int pinCount) {
        if (state instanceof Strike) {
            return "X";
        }

        if (!(state instanceof FirstBowl)) {
            return addBeforeResult(stateResult, state, pinCount);
        }

        if (new Point(pinCount).isGutter()) {
            return "-";
        }

        return String.valueOf(pinCount);
    }

    private static String addBeforeResult(LinkedList<String> stateResult, State state, int pinCount) {
        String beforeResult = stateResult.removeLast();

        if (state instanceof SecondStrike || state instanceof ThirdStrike) {
            return beforeResult +"|X";
        }

        if (state instanceof Spare || state instanceof ThirdSpare) {
            return beforeResult + "|/";
        }

        return formatMiss(pinCount, beforeResult);
    }

    private static String formatMiss(int pinCount, String beforeResult) {
        if (new Point(pinCount).isGutter()) {
            return beforeResult + "|-";
        }

        return beforeResult +"|"+ pinCount;
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
