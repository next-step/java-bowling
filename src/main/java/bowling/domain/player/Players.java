package bowling.domain.player;

import java.util.List;

public class Players {
    private static final int MIN_PLAYER_COUNT = 1;

    private List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    private void validate(List<Player> players) {
        if (players.size() < MIN_PLAYER_COUNT) {
            throw new InvalidPlayersException("플레이어수가 부족합니다.");
        }
    }
}
