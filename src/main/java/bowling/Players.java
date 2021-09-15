package bowling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Players implements Iterable<Player> {
    private final List<Player> players = new ArrayList<>();

    public Players(List<String> playerNames) {
        playerNames.forEach(name -> players.add(new Player(name)));
    }

    public List<Player> getContinuablePlayers() {
        return players.stream()
                .filter(Player::isContinued)
                .collect(Collectors.toList());
    }

    public boolean isContinued() {
        return !getContinuablePlayers().isEmpty();
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
