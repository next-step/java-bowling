package bowling;

import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingApplication {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        int playerCount = inputView.playerCount();
        Players players = inputView.players(playerCount);
        outputView.printBoard(players);

        while (!players.finish()) {
            playFrame(players);
        }
    }

    private static void playFrame(Players players) {
        players.getPlayers()
                .forEach(player -> playTurn(players, player));
    }

    private static void playTurn(Players players, Player player) {
        do {
            int score = inputView.next(player);
            player.next(score);
            outputView.printBoard(players);
        } while (!player.turnFinish());
    }
}
