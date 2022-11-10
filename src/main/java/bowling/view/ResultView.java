package bowling.view;

import bowling.domain.player.Player;
import bowling.domain.player.Players;

public class ResultView {

    private ResultView() {

    }

    public static void printFrameResults(Players players) {
        FramesResult result = new FramesResult();

        System.out.println(result.frameNumberLine());
        for (Player player : players.getPlayers()) {
            printFrameResult(player);
        }
        System.out.println();
    }

    public static void printFrameResult(Player player) {
        FramesResult result = new FramesResult();
        System.out.println(result.getPlayerFrameSigns(player));
        System.out.println(result.getPlayerScores(player));
    }
}
