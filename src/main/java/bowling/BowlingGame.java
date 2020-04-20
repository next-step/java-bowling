package bowling;

import bowling.domain.Player;
import bowling.ui.InputView;
import bowling.ui.OutputView;

public class BowlingGame {
    private final InputView inputView;
    private final OutputView outputView;

    public BowlingGame() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void start() {
        Player player = Player.of(inputView.getPlayer());
        outputView.printFrame(player);

        while (!player.isGameSet()) {
            player.shot(inputView.getShot(player.getCurrentFrameNumber()));
            outputView.printFrame(player);
        }
    }

    public static void main(String[] args) {
        new BowlingGame().start();
    }
}
