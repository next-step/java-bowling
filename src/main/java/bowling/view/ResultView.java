package bowling.view;

import bowling.domain.player.Player;
import bowling.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultView {

    private static final String RESULT_FORMAT = "  %-4s";
    private static final String NAME_FORMAT = " %4s ";
    private static final String DELIMITER = "|";
    private static final String FRAMES_HEADER =
            "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private ResultView() {
    }

    public static void printHeader(final Player player) {
        printFramesHeader();

        printNameInfo(player.getName());
        printFrame(new ArrayList<>(Collections.nCopies(10, StringUtil.EMPTY)));
        System.out.println();
    }

    private static void printFramesHeader() {
        System.out.println(FRAMES_HEADER);
    }

    private static void printNameInfo(final String name) {
        System.out.print(DELIMITER + StringUtil.format(name, NAME_FORMAT) + DELIMITER);
    }

    private static void printFrame(final List<String> strings) {
        strings.forEach(s -> System.out.print(StringUtil.format(s, RESULT_FORMAT) + DELIMITER));
    }
}
