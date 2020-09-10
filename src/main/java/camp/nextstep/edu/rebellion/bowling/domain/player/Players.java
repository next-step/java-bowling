package camp.nextstep.edu.rebellion.bowling.domain.player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    private Players(List<String> participants) {
        this.players = participants.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public static Players join(List<String> participants) {
        return new Players(participants);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public String findNameByOrdinal(int ordinal) {
        return players.get(ordinal)
                .getName();
    }
}
