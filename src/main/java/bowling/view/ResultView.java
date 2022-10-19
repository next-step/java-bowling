package bowling.view;

import bowling.domain.PlayerName;

public class ResultView {
    private final static String SCORE_TABLE_HEAD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private final static String SCORE_TABLE_ROW = "|  %s |      |      |      |      |      |      |      |      |      |      |%n";

    public static void printFrame(PlayerName playerName) {
        System.out.println(SCORE_TABLE_HEAD);
        System.out.printf(SCORE_TABLE_ROW, playerName.name());
    }
}
