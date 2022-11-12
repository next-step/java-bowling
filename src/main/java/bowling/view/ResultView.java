package bowling.view;

import bowling.domain.PlayerName;

public class ResultView {

    private static final String FRAME_NUMBER_PANEL = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String EMPTY_PANEL = "      |";
    public static final String NAME_FORMAT = "|  %s |";

    public void printDefaultPanels(PlayerName playerName) {
        System.out.println(FRAME_NUMBER_PANEL);
        System.out.printf(NAME_FORMAT, playerName);
        for (int i = 0; i < 10; i++) {
            System.out.print(EMPTY_PANEL);
        }
    }
}
