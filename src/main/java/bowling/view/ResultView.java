package bowling.view;

import bowling.domain.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static bowling.util.StringUtils.getCenterFormat;

public class ResultView {

    private static final int MAX_VIEW_FRAME_SIZE = 11;
    private static final int MAX_STRING_SIZE = 6;
    private static final String FIRST_COLUMN_NAME = "NAME";
    private static final String SCOREBOARD_SPLIT_SEPARATOR = "|";

    private static final String HEADER;

    static {
        HEADER = makeHeader();
    }

    private ResultView() {}

    private static String makeHeader() {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, MAX_VIEW_FRAME_SIZE)
                .mapToObj(String::valueOf)
                .forEach(i -> sb.append(SCOREBOARD_SPLIT_SEPARATOR).append(getHeader(i)));
        return sb.toString() + SCOREBOARD_SPLIT_SEPARATOR;
    }

    private static String getHeader(String value) {
        if (Integer.parseInt(value) == 0) {
            return getCenterFormat(FIRST_COLUMN_NAME, MAX_STRING_SIZE);
        }

        String number = String.format("%02d", Integer.parseInt(value));
        return getCenterFormat(number, MAX_STRING_SIZE);
    }

    public static void printHeader(List<Player> players) {
        System.out.println(HEADER);
        for (Player player : players) {
            List<String> results = new LinkedList<>();
            results.add(player.getName());
            printResult(results);
        }
    }

    public static void printScoreboard(List<String> results) {
        System.out.println(HEADER);
        printResult(results);
    }

    private static void printResult(List<String> results) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, MAX_VIEW_FRAME_SIZE)
                .forEach(i -> sb.append(SCOREBOARD_SPLIT_SEPARATOR).append(getResult(results, i)));
        System.out.println(sb.toString() + SCOREBOARD_SPLIT_SEPARATOR) ;
    }

    private static String getResult(List<String> results, int index) {
        if (results.size() <= index) {
            return getCenterFormat("", MAX_STRING_SIZE);
        }
        return getCenterFormat(results.get(index), MAX_STRING_SIZE);
    }
}
