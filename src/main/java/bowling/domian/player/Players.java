package bowling.domian.player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private static final int INITIAL_FRAME_NUMBER = 1;

    private final List<Player> players;
    private int currentFrameNumber;

    private Players(List<Player> players) {
         this.players = players;
         this.currentFrameNumber = INITIAL_FRAME_NUMBER;
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public List<Player> getCurrentPlayers() {
        checkPlayersFrameNumber();

        return players.stream()
                .filter(player -> player.isFrameNumber(currentFrameNumber) && !player.isGameEnd())
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void checkPlayersFrameNumber() {
        boolean needNextFrameNumber = players.stream().noneMatch(player -> player.isFrameNumber(currentFrameNumber));

        if (needNextFrameNumber) {
            this.currentFrameNumber += 1;
        }
    }

    public boolean isGameEnd() {
        return players.stream()
                .filter(Player::isGameEnd)
                .count() == players.size();
    }
}
