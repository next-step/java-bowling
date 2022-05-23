package bowling.ui;

import bowling.domain.Players;

import java.util.List;

public class OutputView {

    private static final int MAX_FRAMES_SIZE = 10;
    private static final String NAME_FORMAT = "| %s  ";
    private static final String FRAME_EXPRESSION_FORMAT = "|  %-3s ";
    private static final String SCORE_FORMAT = "|  %-3d ";
    private static final String EMPTY_PADDING = "|      ";

    private OutputView() {
    }

    public static void printPlayers(Players players) {
        printHeader();
        printBody(players);
        System.out.println();
    }

    private static void printHeader() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10");
    }

    private static void printBody(Players players) {
        List<String> names = players.names();
        List<List<String>> expressions = players.expressions();
        List<List<Integer>> scores = players.scores();
        for (int i = 0; i < players.size(); i++) {
            printName(names.get(i));
            printExpressions(expressions.get(i));
            printScores(scores.get(i));
        }
    }

    private static void printName(String name) {
        System.out.printf(NAME_FORMAT, name);
    }

    private static void printExpressions(List<String> expressions) {
        expressions.forEach(OutputView::printExpression);
        for (int i = 0; i < MAX_FRAMES_SIZE - expressions.size(); i++) {
            printEmptyPadding();
        }
        System.out.println();
    }


    private static void printScores(List<Integer> scores) {
        printEmptyPadding();
        int totalScore = 0;
        for (int score : scores) {
            totalScore += score;
            printScore(totalScore);
        }
        for (int i = 0; i < MAX_FRAMES_SIZE - scores.size(); i++) {
            printEmptyPadding();
        }
        System.out.println();
    }

    private static void printExpression(String expression) {
        System.out.printf(FRAME_EXPRESSION_FORMAT, expression);
    }

    private static void printScore(int score) {
        System.out.printf(SCORE_FORMAT, score);
    }

    private static void printEmptyPadding() {
        System.out.print(EMPTY_PADDING);
    }
}
