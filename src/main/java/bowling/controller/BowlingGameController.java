package bowling.controller;

import java.util.ArrayList;
import java.util.List;

import bowling.domain.player.Player;
import bowling.domain.player.PlayerCount;
import bowling.domain.player.Players;
import bowling.ui.InputView;
import bowling.ui.ResultView;

public class BowlingGameController {
    public BowlingGameController() {
    }

    public void run() {
        Players players = init();
        ResultView.printBowlingBoard(players);

        while (!players.isAllDone()) {
            players.play();
        }
    }

    private Players init() {
        List<Player> players = new ArrayList<>();
        PlayerCount playerCount = PlayerCount.of(InputView.inputPlayerCount());
        for (int i = 1; i < playerCount.toInteger() + 1; i++) {
            players.add(Player.of(InputView.inputPlayerName(i)));
        }
        return new Players(players);
    }
}
