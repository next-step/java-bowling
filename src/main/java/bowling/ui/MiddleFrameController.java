package bowling.ui;

import bowling.domain.Player;

public class MiddleFrameController {
    private Player player;

    public MiddleFrameController(Player player) {
        this.player = player;
    }

    public void doMiddleFrame() {
        while(true) {
            player = doInCurrentFrame(player);
            if (isFinished(player)) {
                break;
            }
            player = doNextFrame(player);
        }
    }

    private Player doNextFrame(Player player) {
        player = player.toNextFrame(
                InputView.getNumberOfHitPin(player.getCurrentFrameIndex() + 1));

        OutputView.printPlayerResult(player);
        return player;
    }

    private Player doInCurrentFrame(Player player) {
        while (!player.isCurrentFrameCompleted()) {
            player = player.bowlCurrentFrame(
                    InputView.getNumberOfHitPin(player.getCurrentFrameIndex()));

            OutputView.printPlayerResult(player);
        }
        return player;
    }

    private boolean isFinished(Player player) {
        return (player.getCurrentFrameIndex() == 10) && (player.isCurrentFrameCompleted());
    }
}
