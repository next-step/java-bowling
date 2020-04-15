package bowling.view;

import bowling.controller.BowlingGame;
import bowling.controller.BowlingGame2;
import bowling.domain.frame.FrameNumber;
import bowling.domain.player.Player;
import bowling.view.format.StateFormatter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {
    private static final String DELIMITER = "|";
    private static final String EMPTY_STRING = "";
    private static final String FRAMES_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private OutputView() {}

    private static void printFramesHeader() {
        System.out.println(FRAMES_HEADER);
    }

    public static void printOverHead(BowlingGame2 game) {
        printFramesHeader();
        printFrameState(game.getCurrentPlayer(), game.getResult());
    }

    private static void printFrameState(final Player currentPlayer, final List<String> states) {
        String formatName = formatName(currentPlayer.name());
        String formatStates = states.stream()
                                    .map(OutputView::formatState)
                                    .collect(Collectors.joining(DELIMITER, EMPTY_STRING, DELIMITER));
        System.out.println(formatName + formatStates);
    }

    private static String formatState(final String state) {
        return StateFormatter.format(state);
    }

    private static String formatName(final String name) {
        return DELIMITER + name + DELIMITER;
    }

//    public static void printOverHead(BowlingGame frameStates) {
//        printFramesHeader();
//        final String[] table = frameStates.split(",");
//        final String result = Arrays.stream(table)
//                .collect(Collectors.joining(DELIMITER, DELIMITER, DELIMITER));
//        System.out.println(result);
//        System.out.println();
//    }
}
