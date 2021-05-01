package bowling.ui;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import bowling.domain.player.Player;
import bowling.domain.state.State;
import bowling.domain.state.States;

public class ResultView {
    private static final int LAST_FRAME = 10;
    private static final String PARTITION = "|";
    private static final String FRAME_TITLE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String EMPTY_STRING = "";


    public static void printInitBowlingBoard(Player player) {
        printTitleFrames();
        System.out.print(PARTITION + formatting(player.name()));
        printEmptyFrames(player.currentFrameNumber() - 1);
    }

    public static void printBowlingBoard(Player player) {
        printTitleFrames();
        printFrameStates(player);
        printEmptyFrames(player.currentFrameNumber());
    }

    private static void printTitleFrames() {
        System.out.println(FRAME_TITLE);
    }

    private static void printEmptyFrames(int size) {
        Stream.generate(() -> formatting(EMPTY_STRING))
            .limit(LAST_FRAME - size)
            .forEach(System.out::print);
        System.out.println("\n");
    }

    private static void printFrameStates(Player player) {
        System.out.print(PARTITION + formatting(player.name()));

        player.states().stream()
            .map(ResultView::printStates)
            .forEach(System.out::print);
    }

    private static String printStates(States states) {
        String expression = states.states()
            .stream()
            .map(ResultView::replaceNull)
            .collect(Collectors.joining(PARTITION));
        return formatting(expression);
    }

    private static String replaceNull(final State state) {
        if (state == null) {
            return EMPTY_STRING;
        }
        return state.toSymbol();
    }

    private static String formatting(String input) {
        return String.format("  %-3s " + PARTITION, input);
    }
}
