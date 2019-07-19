package view;

import domain.BowlingGame;
import domain.frame.Frame;
import domain.state.State;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {
    private static final String UPPER_SIDE_OF_SCORE_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    public static void printBoard(BowlingGame bowlingGame) { //TODO: 이 부분 완전히 개선하기
        System.out.println(UPPER_SIDE_OF_SCORE_BOARD);

        List<String> result = new ArrayList<>();
        String player = bowlingGame.getPlayer().getName();
        result.add("| " + player);

        List<String> states = bowlingGame.getFrames()
                .stream()
                .map(Frame::getState)
                .map(State::printState)
                .collect(Collectors.toList());

        List<String> emptyFrames = IntStream.rangeClosed(states.size(), 10)
                .mapToObj((integer) -> "  ")
                .collect(Collectors.toList());

        result.addAll(states);
        result.addAll(emptyFrames);
        System.out.println(String.join("  |  ", result));

        printEmptyLine();
    }

    private static void printEmptyLine() {
        System.out.println();
    }
}
