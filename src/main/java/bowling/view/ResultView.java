package bowling.view;

import bowling.domain.Frames;
import bowling.domain.PlayerName;

public class ResultView {

    private static final String FRAME_NUMBER_PANEL =
            "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String EMPTY_PANEL = "      |";
    private static final String NAME_FORMAT = "|  %s |";
    static final int MAX_FRAME_NUMBER = 10;
    static final int STRIKE_NUMBER = 10;
    static final int SPARE_DECIDE_NUMBER = 10;
    static final String MARK_DIVIDER = "|";
    static final String STRIKE_MARK = "X";
    static final String SPARE_MARK = "/";
    static final String GUTTER_MARK = "-";
    public static final String FRAME_TRY_MSG_FORMAT = "\n%d프레임 투구 : %s\n";
    public static final String EACH_FRAME_MARK_FORMAT = "  %-3s |";

    public void printDefaultPanels(PlayerName playerName) {
        System.out.println(FRAME_NUMBER_PANEL);
        System.out.printf(NAME_FORMAT, playerName);
        for (int i = 0; i < MAX_FRAME_NUMBER; i++) {
            System.out.print(EMPTY_PANEL);
        }
        System.out.println();
    }

    public void printGameScores(PlayerName playerName, Frames frames) {
        System.out.printf(FRAME_TRY_MSG_FORMAT, frames.getLastOrder(), frames.getLastTry());
        System.out.println(FRAME_NUMBER_PANEL);
        System.out.printf(NAME_FORMAT, playerName);
        frames.getFrames().forEach(frame ->
                System.out.printf(EACH_FRAME_MARK_FORMAT, Mark.print(frame)));
        for (int i = 0; i < MAX_FRAME_NUMBER - frames.size(); i++) {
            System.out.print(EMPTY_PANEL);
        }
        System.out.println();
    }

}
