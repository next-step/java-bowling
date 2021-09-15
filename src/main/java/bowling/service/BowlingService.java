package bowling.service;

import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingService {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public void play(Players players) {
        while (!players.finish()) {
            playFrame(players);
        }
    }

    private void playFrame(Players players) {
        players.getPlayers()
                .forEach(player -> playTurn(players, player));
    }

    private void playTurn(Players players, Player player) {
        do {
            int score = inputView.next(player);
            player.next(score);
            outputView.printBoard(players);
        } while (!player.turnFinish());
    }
}
