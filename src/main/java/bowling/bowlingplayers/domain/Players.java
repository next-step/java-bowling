package bowling.bowlingplayers.domain;

import java.util.*;

public class Players {

    private final List<Player> players;
    private int currentFrame = 1;

    public Players(List<String> playersNames) {
        players = new ArrayList<>();

        for (String name : playersNames) {
            Player player = new Player(name);
            players.add(player);
        }
    }

    public Player pitchingPlayer() {
        Player player = players.stream()
                .filter(p -> p.currentFrame() == currentFrame)
                .findFirst()
                .orElse(null);

        if (player == null) {
            currentFrame++;
            return players.get(0);
        }

        return player;
    }

    public List<Player> players() {
        return Collections.unmodifiableList(players);
    }

    public boolean end() {
        return players.get(players.size() - 1).end();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Players)) return false;
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }
}
