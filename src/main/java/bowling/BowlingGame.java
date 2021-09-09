package bowling;

import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGame {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        String playerName = inputView.playerName();
        Player player = new Player(playerName);
        outputView.printBoard(player);

        int score;
        for (int i = player.getFrames().currentFrameNo(); i < 11; ) {
            score = inputView.next(player);
            player.getFrames().next(score);
            outputView.printBoard(player);
            i = player.getFrames().currentFrameNo();
        }
    }
}
