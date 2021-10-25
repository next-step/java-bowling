package bowling.service;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.presentation.input.ScoreInputView;
import bowling.presentation.output.PlayersFrameOutputView;

public class BowlingService {

    private static final int LAST_FRAME = 10;

    private final Players players;

    public BowlingService(Players players) {
        this.players = players;
    }


    public static BowlingService create(Players players) {
        return new BowlingService(players);
    }

    public void start() {
        printBowlingStartScreen(players);
        bowl();
    }

    private void printBowlingStartScreen(Players players) {
        PlayersFrameOutputView.create(players).print();
    }

    private void bowl() {
        for (int i = 0; i < LAST_FRAME; i++) {
            bowlEachFrame();
        }
    }

    private void bowlEachFrame() {
        for (Player player : players.all()) {
            bowl(player);
        }
    }

    private void bowl(Player player) {
        player = player.bowl(ScoreInputView.create().input(player.getName()));
        PlayersFrameOutputView.create(players).print();
        while (!player.nowFrameEnd()) {
            player = player.bowl(ScoreInputView.create().input(player.getName()));
            PlayersFrameOutputView.create(players).print();
        }
    }

}
