package bowling.view;

import bowling.domain.Player;

import java.util.stream.Collectors;

public class OutputView {
    private static final String DASH_BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String DASH_BOARD_FORMAT = "|%s|";
    private static final String DASH_BOARD_SEPARATOR = "|";
    private static final int FRAME_FORMAT_LENGTH = 4;
    private static final String FRAME_FORMAT_FIVE_LENGTH = " %-5s";
    private static final String FRAME_FORMAT_FOUR_LENGTH = "  %-4s";

    public static void printDashBoard(Player player) {
        printHeader();
        printPlayer(player);
    }

    private static void printPlayer(Player player) {
        String result = player.getResult().stream()
                .map(s -> String.format(frameFormat(s.length()), s))
                .collect(Collectors.joining(DASH_BOARD_SEPARATOR));

        System.out.println(String.format(DASH_BOARD_FORMAT, result));
    }

    private static void printHeader() {
        System.out.println(DASH_BOARD_HEADER);
    }

    private static String frameFormat(int length) {
        return length > FRAME_FORMAT_LENGTH ? FRAME_FORMAT_FIVE_LENGTH : FRAME_FORMAT_FOUR_LENGTH;
    }
}
