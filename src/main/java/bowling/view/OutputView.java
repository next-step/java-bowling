package bowling.view;

import java.util.Arrays;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = "|";
    private static final String FRAMES_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private OutputView() {}

    private static void printFramesHeader() {
        System.out.println(FRAMES_HEADER);
    }

    public static void printOverHead(String frameStates) {
        printFramesHeader();
        final String[] table = frameStates.split(",");
        final String result = Arrays.stream(table)
                                    .collect(Collectors.joining(DELIMITER, DELIMITER, DELIMITER));
        System.out.println(result);
        System.out.println();
    }
}
