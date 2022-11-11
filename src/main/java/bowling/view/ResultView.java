package bowling.view;

import bowling.domain.player.Player;
import bowling.domain.player.Players;

public class ResultView {
    private static final FramesResult RESULT = new FramesResult();

    private ResultView() {

    }

    public static void printFrameResults(Players players) {
        System.out.println(RESULT.frameNumberLine());

        for (Player player : players.getPlayers()) {
            printResultOfPlayer(player);
        }
        System.out.println();
    }

    private static void printResultOfPlayer(Player player) {
        System.out.println(RESULT.getPlayerFrameSigns(player));
        System.out.println(RESULT.getPlayerScores(player));
    }
}
