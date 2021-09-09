package bowling.view;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.player.Player;

import java.util.List;

public class OutputView {
    private static final String TABLE_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String COLUMN_INIT = "|";
    private static final String COLUMN_SEPARATOR = "  %3s |";
    private static final String EMPTY_SCORE = "  ";
    private static final int NORMAL_FRAME_SIZE = 9;

    public void printBoard(Player player) {
        System.out.println(TABLE_HEADER);
        System.out.printf(COLUMN_INIT + COLUMN_SEPARATOR, player.getPlayerName());

        for (int i = 0; i < NORMAL_FRAME_SIZE; i++) {
            printNormalFrames(player, i);
        }
        printFinalFrames(player);
        System.out.println();
    }

    private void printNormalFrames(Player player, int i) {
        List<NormalFrame> normalFrames = player.getFrames().getNormalFrames();
        if (i < normalFrames.size()
                && normalFrames.get(i).getNormalScores().getScores().size() > 0) {
            System.out.printf(COLUMN_SEPARATOR, normalFrames.get(i).getNormalScores().toString());
            return;
        }
        System.out.printf(COLUMN_SEPARATOR, EMPTY_SCORE);
    }

    private void printFinalFrames(Player player) {
        List<FinalFrame> finalFrames = player.getFrames().getFinalFrames();
        if(finalFrames.size() !=0){
            System.out.printf(COLUMN_SEPARATOR, finalFrames.get(0).getFinalScores().toString());
            return;
        }
        System.out.printf(COLUMN_SEPARATOR, EMPTY_SCORE);
    }
}
