package view;

import domain.BowlingGame;
import domain.Score;
import domain.frame.Frame;
import domain.frame.FrameResult;
import domain.state.State;
import utils.PrintUtils;

import java.util.List;
import java.util.stream.IntStream;

import static domain.frame.FrameIndex.MAXIMUM_FRAME_INDEX;
import static domain.frame.FrameResult.UNFINISHED_SCORE;

public class OutputView {
    private static final String UPPER_SIDE_OF_SCORE_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    public static final String SEPARATOR = "|";
    public static final String EMPTY_SYMBOL = " ";

    public static void printBoard(BowlingGame bowlingGame) {
        System.out.println(UPPER_SIDE_OF_SCORE_BOARD);
        System.out.println(printStates(bowlingGame));
        System.out.println(printScores(bowlingGame));
        printEmptyLine();
    }

    private static String printStates(BowlingGame bowlingGame) {
        String player = bowlingGame.getPlayerName();
        StringBuilder statesBuilder = new StringBuilder();
        statesBuilder.append(SEPARATOR)
                .append(PrintUtils.centralize(player))
                .append(SEPARATOR);
        List<String> finalStates = bowlingGame.getFrameResults().getFinalStates();

        finalStates.stream()
                .map(PrintUtils::centralize)
                .map(String -> String.concat(SEPARATOR))
                .forEach(statesBuilder::append);

        fillUnplayedFrames(finalStates.size(), statesBuilder);

        return statesBuilder.toString();
    }

    private static String printScores(BowlingGame bowlingGame) {
        StringBuilder scoresBuilder = new StringBuilder();
        scoresBuilder.append(SEPARATOR)
                .append(PrintUtils.centralize(EMPTY_SYMBOL))
                .append(SEPARATOR);

        List<Integer> finalScores = bowlingGame.getFrameResults().getFinalScores();

        finalScores.stream()
                .map(OutputView::removeZero)
                .map(PrintUtils::centralize)
                .map(OutputView::partitioning)
                .forEach(scoresBuilder::append);

        fillUnplayedFrames(finalScores.size(), scoresBuilder);

        return scoresBuilder.toString();
    }

    private static String partitioning(String data) {
        return data.concat(SEPARATOR);
    }

    private static String removeZero(Integer score) {
        if (score == 0) {
            return EMPTY_SYMBOL;
        }
        return score.toString();
    }

    private static void fillUnplayedFrames(int size, StringBuilder stringBuilder) {
        IntStream.range(size, MAXIMUM_FRAME_INDEX)
                .mapToObj(num -> PrintUtils.centralize(EMPTY_SYMBOL))
                .map(OutputView::partitioning)
                .forEach(stringBuilder::append);
    }

    private static void printEmptyLine() {
        System.out.println();
    }
}
