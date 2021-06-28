package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Bowling {
    private final List<Player> players;
    private final int currentFrameNumber;

    public Bowling(List<Player> players) {
        this(players, 1);
    }

    private Bowling(List<Player> players, int currentFrameNumber) {
        this.players = players;
        this.currentFrameNumber = currentFrameNumber;
    }

    public Player currentPlayer() {
        return players.stream()
                .filter(player -> !player.playedFrame(currentFrameNumber))
                .findFirst()
                .orElse(null);
    }

    public Bowling play(int knockedPinsCount) {
        final List<Player> newPlayers = players.stream()
                .map(player -> player.equals(currentPlayer()) ? player.play(knockedPinsCount) : player)
                .collect(Collectors.toList());

        final int newCurrentFrameNumber = newPlayers.stream()
                .allMatch(player -> player.playedFrame(currentFrameNumber)) ?
                currentFrameNumber + 1 : currentFrameNumber;

        return new Bowling(newPlayers, newCurrentFrameNumber);
    }

    public boolean playing() {
        return players.stream()
                .anyMatch(Player::playing);
    }

    public List<Player> players() {
        return Collections.unmodifiableList(players);
    }
}
