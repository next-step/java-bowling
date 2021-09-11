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

        while (!player.isFinish()) {
            score = inputView.next(player);
            player.next(score);
            outputView.printBoard(player);
        }
    }
}
