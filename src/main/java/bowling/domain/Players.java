package bowling.domain;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Players implements Iterable<Player> {
    final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public boolean playing() {
        return players.stream()
                .anyMatch(Player::playing);
    }

    public Player playingPlayer(int frameNumber) {
        return players.stream()
                .filter(player -> !player.played(frameNumber))
                .findFirst()
                .orElse(null);
    }

    public boolean played(int frameNumber) {
        return players.stream()
                .allMatch(player -> player.played(frameNumber));
    }

    public Players play(int currentFrameNumber, int knockedPinsCount) {
        return new Players(
                players.stream()
                        .map(player -> player.equals(playingPlayer(currentFrameNumber)) ? player.play(knockedPinsCount) : player)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }

    @Override
    public void forEach(Consumer<? super Player> action) {
        players.forEach(action);
    }

    @Override
    public Spliterator<Player> spliterator() {
        return players.spliterator();
    }
}
