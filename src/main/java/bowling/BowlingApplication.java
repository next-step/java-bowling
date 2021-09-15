package bowling;

import bowling.domain.player.Players;
import bowling.service.BowlingService;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingApplication {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        int playerCount = inputView.playerCount();
        Players players = inputView.players(playerCount);
        outputView.printBoard(players);

        BowlingService bowlingService = new BowlingService();
        bowlingService.play(players);
    }
}
