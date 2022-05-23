package bowling;

import bowling.domain.Players;
import bowling.ui.InputView;
import bowling.ui.OutputView;

public class Main {

    public static void main(String[] args) {
        Players players = InputView.promptPlayers();

        while (!players.finished()) {
            players.bowl(InputView.promptPinFor(players.currentBowlerName()));
            OutputView.printPlayers(players);
        }
    }
}
