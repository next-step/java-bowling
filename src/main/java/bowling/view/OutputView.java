package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Player;

import java.util.List;

public class OutputView {
    private static final String TABLE_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String COLUMN_SEPARATOR = "  %3s |";

    public void printBoard(Player player) {
        System.out.println(TABLE_HEADER);
        System.out.printf("|" + COLUMN_SEPARATOR, player.getName());

        List<Frame> frames;
        for (int i = 0; i < 10; i++) {
            frames = player.getFrames().getFrames();
            if (i < frames.size()
                    && frames.get(i).getScores().getScores().size() > 0) {
                System.out.printf(COLUMN_SEPARATOR, frames.get(i).getScores().toString());
            } else {
                System.out.printf(COLUMN_SEPARATOR, "  ");
            }
        }
        System.out.println();
    }
}
