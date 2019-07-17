package view;

import domain.BowlingGame;
import domain.Player;
import domain.frame.Frame;
import domain.state.State;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String UPPER_SIDE_OF_SCORE_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    public static void printBoard(BowlingGame bowlingGame) {
        System.out.println(UPPER_SIDE_OF_SCORE_BOARD);

        Player player = bowlingGame.getPlayer();
        List<Frame> frames = bowlingGame.getFrames();

        String result = frames.stream()
                .map(Frame::getState)
                .map(State::printState)
                .collect(Collectors.joining("  |  "));

        System.out.println(result);

        printEmptyLine();
    }

    private static void printEmptyLine() {
        System.out.println();
    }
}
