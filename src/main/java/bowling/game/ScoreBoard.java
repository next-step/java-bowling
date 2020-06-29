package bowling.game;

import bowling.game.Frames;
import bowling.player.domain.Player;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ScoreBoard {
    private final Map<Player, Frames> playersFrames;

    public ScoreBoard(List<Player> players) {
        this.playersFrames = createPlayersFrames(players);
    }

    private Map<Player, Frames> createPlayersFrames(List<Player> players) {
        return players.stream()
                .collect(toMap(player -> player, player -> new Frames()));
    }

    public Frames findByPlayer(Player player) {
        validatePlayer(player);
        return playersFrames.get(player);
    }

    public Frames findByName(String name) {
        return playersFrames.entrySet().stream()
                .filter(entry -> entry.getKey().isSameName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지않는 플레이어 입니다."))
                .getValue();
    }

    private void validatePlayer(Player player) {
        if (!playersFrames.containsKey(player)) {
            throw new IllegalArgumentException("존재하지않는 플레이어 입니다.");
        }
    }
}
