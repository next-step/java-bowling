package bowling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Players implements Iterable<Player> {
    private final List<Player> players = new ArrayList<>();

    public Players(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());

        this.players.addAll(players);
    }

    public boolean isContinued() {
        return !getContinuablePlayers().isEmpty();
    }

    private List<Player> getContinuablePlayers() {
        return players.stream()
                .filter(Player::isContinued)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
