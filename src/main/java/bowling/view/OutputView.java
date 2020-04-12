package bowling.view;

import bowling.domain.Player;

public class OutputView {
    private static final String FRAME_SECTION = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String SCORE_SECTION = "|%5s|%5s|%5s|%5s|%5s|%5s|%5s|%5s|%5s|%5s|%5s|\n";

    public void result(Player player) {
        System.out.println(FRAME_SECTION);
        System.out.printf(SCORE_SECTION);
    }
}
