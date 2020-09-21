package bowling.domain;

import bowling.exception.NotExistCurrentFramePlayerException;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private static final String MIN_PLAYERS_EXCEPTION = "참여자는 최소 1명 이상이여야합니다.";
    private final List<Player> players = new ArrayList<>();

    public Players(int howManyPeople) {
        if (howManyPeople <= 0) {
            throw new IllegalArgumentException(MIN_PLAYERS_EXCEPTION);
        }
    }

    public void addPlayers(Player player) {
        players.add(player);
    }

    public Player turnPlayer() {
        return currentPlayer();
    }

    private int minPlayFrameIndex() {
        return players.stream().map(player -> player.getLastFrameIndex()).min(Integer::compare).orElse(1);
    }

    private Player currentPlayer() {
        return players.stream()
                .filter(player -> player.isSameFrameIndex(minPlayFrameIndex()))
                .filter(player -> !player.isEndGame())
                .limit(1)
                .findFirst()
                .orElseThrow(NotExistCurrentFramePlayerException::new);
    }

    public boolean isAllPlayerEndGame() {
        return players.stream().noneMatch(player -> player.isEndGame() == false);
    }
}
