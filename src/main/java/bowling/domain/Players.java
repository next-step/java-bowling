package bowling.domain;

import bowling.exception.NotExistCurrentFramePlayerException;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private static final String MIN_PLAYERS_EXCEPTION = "참여자는 최소 1명 이상이여야합니다.";
    private static final List<Player> players = new ArrayList<>();
    private int playFrameIndex = 0;

    public Players(int howManyPeople) {
        if(howManyPeople <= 0) {
            throw new IllegalArgumentException(MIN_PLAYERS_EXCEPTION);
        }
    }

    public void addPlayers(Player player) {
        players.add(player);
    }

    public Player turnPlayer() {
        if(isAllNowFrameEnd()) {
            playFrameIndex++;
        }

        return currentPlayer();
    }

    private Player currentPlayer() {
        return players.stream()
                .filter(player -> player.getLastFrameIndex() == playFrameIndex)
                .filter(player -> player.isEndGame() == false)
                .limit(1)
                .findFirst()
                .orElseThrow(NotExistCurrentFramePlayerException::new);
    }

    public boolean isAllNowFrameEnd() {
        if (players.stream().anyMatch(player -> player.getLastFrameIndex() == playFrameIndex)){
            return false;
        }
        return true;
    }

    public boolean isAllPlayerEndGame() {
        return players.stream().noneMatch(player -> player.isEndGame() == false);
    }
}
