package bowling.domain.player;

import java.util.Collections;
import java.util.List;

import bowling.ui.InputView;
import bowling.ui.ResultView;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public List<Player> toList() {
        return Collections.unmodifiableList(players);
    }

    public boolean isAllDone() {
        return players.stream()
            .allMatch(Player::isDone);
    }

    public void play() {
        for (Player player : players) {
            turn(player);
            player.changeNextFrame();
        }
    }

    private void turn(Player player) {
        while(!player.isTurnDone()) {
            player.bowl(InputView.inputBowlingPin(player.name()));
            ResultView.printBowlingBoard(this);
        }
    }
}
