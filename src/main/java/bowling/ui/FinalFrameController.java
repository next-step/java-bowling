package bowling.ui;

import bowling.domain.Player;

public class FinalFrameController {
    private Player player;

    public FinalFrameController(Player player) {
        this.player = player;
    }

    public void doFinalFrame() {
        player = player.toFinalFrame(InputView.getNumberOfHitPin(10));

        OutputView.printPlayerResult(player);

        doFinalFrameInProgress(player);
    }

    private void doFinalFrameInProgress(Player player) {
        while(!player.isCurrentFrameCompleted()) {
            player = player.bowlCurrentFrame(InputView.getNumberOfHitPin(10));

            OutputView.printPlayerResult(player);
        }
    }
}
