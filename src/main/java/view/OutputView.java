package view;

import domain.BowlingGame;
import domain.Score;
import domain.frame.Frame;
import domain.state.State;
import utils.PrintUtils;

import java.util.stream.IntStream;

import static domain.frame.FrameIndex.MAXIMUM_FRAME_INDEX;

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

        bowlingGame.getFrames()
                .stream()
                .map(Frame::getState)
                .map(State::printState)
                .map(PrintUtils::centralize)
                .map(String -> String.concat(SEPARATOR))
                .forEach(statesBuilder::append);

        fillUnplayedFrames(bowlingGame, statesBuilder);

        return statesBuilder.toString();
    }

    private static String printScores(BowlingGame bowlingGame) {
        StringBuilder scoresBuilder = new StringBuilder();
        scoresBuilder.append(SEPARATOR)
                .append(PrintUtils.centralize(EMPTY_SYMBOL))
                .append(SEPARATOR);

        bowlingGame.getFrames()
                .stream()
                .map(Frame::getScore)
                .map(Score::getScore)
                .map(String::valueOf)
                .forEach(scoresBuilder::append);

        fillUnplayedFrames(bowlingGame, scoresBuilder);

        return scoresBuilder.toString();
    }

    private static void fillUnplayedFrames(BowlingGame bowlingGame, StringBuilder stringBuilder) {
        IntStream.range(bowlingGame.sizeOfFrames(), MAXIMUM_FRAME_INDEX)
                .mapToObj(num -> PrintUtils.centralize(EMPTY_SYMBOL))
                .map(String -> String.concat(SEPARATOR))
                .forEach(stringBuilder::append);
    }

    private static void printEmptyLine() {
        System.out.println();
    }
}
