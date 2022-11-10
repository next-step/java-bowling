package bowling.view;

import bowling.domain.player.Player;

public class ResultView {

    private ResultView() {

    }

    public static void printFrameResult(Player player) {
        FramesResult result = new FramesResult();

        System.out.println(result.frameNumberLine());
        System.out.println(result.getPlayerFrameSigns(player));
        System.out.println(result.getPlayerScores(player) + System.lineSeparator());
    }
}
