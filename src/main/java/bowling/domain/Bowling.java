package bowling.domain;

import java.util.List;

public class Bowling {
    private static final int INITIAL_FRAME_NUMBER = 1;

    private final Players players;
    private final int currentFrameNumber;

    public Bowling(List<Player> players) {
        this(new Players(players), INITIAL_FRAME_NUMBER);
    }

    private Bowling(Players players, int currentFrameNumber) {
        this.players = players;
        this.currentFrameNumber = currentFrameNumber;
    }

    public Player currentPlayer() {
        return players.playingPlayer(currentFrameNumber);
    }

    public Bowling play(int knockedPinsCount) {
        final Players newPlayers = players.play(currentFrameNumber, knockedPinsCount);

        final int newCurrentFrameNumber = newPlayers.played(currentFrameNumber) ?
                currentFrameNumber + 1 : currentFrameNumber;

        return new Bowling(newPlayers, newCurrentFrameNumber);
    }

    public boolean playing() {
        return players.playing();
    }

    public Players players() {
        return players;
    }
}
