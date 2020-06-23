package bowling.ui;

import bowling.domain.Player;

public class FirstFrameController {
    private Player player;

    public FirstFrameController(Player player) {
        this.player = player;
    }

    public Player doFirstFrame() {
        Player player = bowlFirst(this.player);
        player = doNotCompletedFirstFrame(player);
        return player;
    }

    private Player bowlFirst(Player initPlayer) {
        Player player = initPlayer.bowlFirst(InputView.getNumberOfHitPin(1));

        OutputView.printPlayerResult(player);
        return player;
    }

    private Player doNotCompletedFirstFrame(Player player) {
        if (!player.isCurrentFrameCompleted()) {
            player = player.bowlCurrentFrame(InputView.getNumberOfHitPin(1));

            OutputView.printPlayerResult(player);
        }
        return player;
    }
}
