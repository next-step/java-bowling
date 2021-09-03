package bowling.domain.player;

import bowling.domain.pins.Pins;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingPlayerBoards {

    private static final int FIRST_PLAYER_INDEX = 0;
    private final List<BowlingPlayerBoard> players;
    private BowlingPlayerBoard currentPlayer;

    private BowlingPlayerBoards(List<BowlingPlayerBoard> players) {
        this.players = players;
        this.currentPlayer = this.players.get(FIRST_PLAYER_INDEX);
    }

    public static BowlingPlayerBoards of(List<String> playerNames) {
        return new BowlingPlayerBoards(
                playerNames.stream()
                        .map(BowlingPlayerBoard::of)
                        .collect(Collectors.toList())
        );
    }

    public void bowl(Pins pins) {
        currentPlayer.bowl(pins);
        playerRotate();
    }

    public boolean isFinish() {
        return getLastPlayer().isFinish();
    }

    public BowlingPlayerBoard getCurrentPlayer() {
        return currentPlayer;
    }

    public List<BowlingPlayerBoard> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void playerRotate() {
        if (currentPlayer.isTurnOver()) {
            int currentPlayerIndex = players.indexOf(currentPlayer);
            int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
            currentPlayer = players.get(nextPlayerIndex);
        }
    }

    private BowlingPlayerBoard getLastPlayer() {
        return players.get(players.size() - 1);
    }
}
