package bowling.view;

import bowling.domain.frame.NormalFrame;
import bowling.domain.player.Player;

import java.util.List;

public class OutputView {
    private static final String TABLE_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String COLUMN_SEPARATOR = "  %3s |";

    public void printBoard(Player player) {
        System.out.println(TABLE_HEADER);
        System.out.printf("|" + COLUMN_SEPARATOR, player.getName());

        List<NormalFrame> normalFrames;
        for (int i = 0; i < 9; i++) {
            normalFrames = player.getFrames().getNormalFrames();
            if (i < normalFrames.size()
                    && normalFrames.get(i).getNormalScores().getScores().size() > 0) {
                System.out.printf(COLUMN_SEPARATOR, normalFrames.get(i).getNormalScores().toString());
            } else {
                System.out.printf(COLUMN_SEPARATOR, "  ");
            }
        }

        if(player.getFrames().getFinalFrames().size() !=0){
            System.out.printf(COLUMN_SEPARATOR, player.getFrames().getFinalFrames().get(0).getFinalScores().toString());
        } else {
            System.out.printf(COLUMN_SEPARATOR, "  ");
        }
        System.out.println();
    }
}
