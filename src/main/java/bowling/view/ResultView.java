package bowling.view;

import bowling.domain.Player;
import bowling.domain.Players;

public class ResultView {

    /**
     * | NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
     * |  PJS |  X   |  8   |      |      |      |      |      |      |      |      |
     */

    private static final FramesResult RESULT = FramesResult.init();

    private ResultView() {}

    public static void printFrameResults(final Players players) {

        System.out.println(RESULT.frameNumberLine());
        for (Player player : players.getPlayers()) {
            resultPlayer(player);
        }
        System.out.println();
    }

    private static void resultPlayer(final Player player) {

        System.out.println(RESULT.playerFrameSigns(player));
        System.out.println(RESULT.playerScores(player));
    }
}
