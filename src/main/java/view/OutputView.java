package view;

import domain.BowlingGame;
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
        printEmptyLine();
    }

    private static String printStates(BowlingGame bowlingGame) {
        String player = bowlingGame.getPlayerName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SEPARATOR + PrintUtils.centralize(player) + SEPARATOR);

        bowlingGame.getFrames()
                .stream()
                .map(Frame::getState)
                .map(State::printState)
                .map(PrintUtils::centralize)
                .map(String -> String.concat(SEPARATOR))
                .forEach(stringBuilder::append);

        IntStream.range(bowlingGame.sizeOfFrames(), MAXIMUM_FRAME_INDEX)
                .mapToObj(num -> PrintUtils.centralize(EMPTY_SYMBOL))
                .map(String -> String.concat(SEPARATOR))
                .forEach(stringBuilder::append);

        return stringBuilder.toString();
    }

    private static void printEmptyLine() {
        System.out.println();
    }
}
