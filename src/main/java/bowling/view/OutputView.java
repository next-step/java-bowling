package bowling.view;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

public class OutputView {

    private OutputView() {
    }

    public static void printDefaultScoreBoard(Player player) {
        System.out.println(ViewMessage.SCORE_BOARD_HEADER);
        System.out.printf(ViewMessage.DEFAULT_SCORE_BOARD, player.getName());
    }

    public static void printScoreBoard(Player player, Frames frames) {
        System.out.println(ViewMessage.SCORE_BOARD_HEADER);
        
    }
}
