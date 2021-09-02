package bowling.domain.player;

import bowling.domain.frame.Frames;
import bowling.domain.pins.Pins;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingPlayers {

    private static final int FIRST_PLAYER_INDEX = 0;

    private BowlingPlayer currentPlayer;
    private final List<BowlingPlayer> players;

    private BowlingPlayers(List<BowlingPlayer> players) {
        this.players = players;
        this.currentPlayer = this.players.get(FIRST_PLAYER_INDEX);
    }

    public static BowlingPlayers of(List<String> playerNames) {
        return new BowlingPlayers(
                playerNames.stream()
                        .map(BowlingPlayer::of)
                        .collect(Collectors.toList())
        );
    }

    public void bowl(Pins pins) {
        Frames frames = currentPlayer.getFrames();
        frames.bowl(pins);

        playerRotate();
    }

    public boolean isFinish() {
        return getLastPlayer().getFrames().isFinish();
    }

    public BowlingPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public List<BowlingPlayer> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void playerRotate() {
        if (currentPlayer.isTurnOver()) {
            int currentPlayerIndex = players.indexOf(currentPlayer);
            int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
            currentPlayer = players.get(nextPlayerIndex);
        }
    }

    private BowlingPlayer getLastPlayer() {
        return players.get(players.size() - 1);
    }
}
