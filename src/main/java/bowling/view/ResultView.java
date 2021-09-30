package bowling.view;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {

    private static final String SCORE_NAME_BOARD = "| NAME |";
    private static final String SCORE_BOARD_FORMAT = "  %02d  |";

    private ResultView() {
    }

    public static void printScoreBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append(SCORE_NAME_BOARD);
        sb.append(IntStream.rangeClosed(1, 10)
            .mapToObj(ResultView::toBoardFormat)
            .collect(Collectors.joining()));
        System.out.println(sb.toString());
    }

    private static String toBoardFormat(int num) {
        return String.format(SCORE_BOARD_FORMAT, num);
    }

}
