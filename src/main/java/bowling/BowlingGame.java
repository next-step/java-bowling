package bowling;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.dto.PlayersDto;
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
        Players players = Players.of(inputView.getPlayers());
        outputView.printFrame(new PlayersDto(players));

        while (!players.getPlayers().stream().allMatch(Player::isGameSet)) {
            players.shot(inputView.getShot(players.getCurrentPlayerName()));
            outputView.printFrame(new PlayersDto(players));
        }
    }

    public static void main(String[] args) {
        new BowlingGame().start();
    }
}
