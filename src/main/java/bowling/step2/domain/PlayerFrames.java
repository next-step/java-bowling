package bowling.step2.domain;

import bowling.step2.domain.frame.Frame;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

public class PlayerFrames {
    private final Map<Player, Frames> playerFrames;

    private PlayerFrames (Map<Player, Frames> playerFrames) {
        this.playerFrames = playerFrames;
    }

    public static PlayerFrames of (Map<Player, Frames> playerFrames) {
        return new PlayerFrames(playerFrames);
    }

    public static PlayerFrames init (Players players) {
        return players.stream()
                      .collect(collectingAndThen(
                          toMap(player -> player, player -> Frames.init()),
                          PlayerFrames::of
                      ));
    }

    public Stream<Frame> getPreviewOf (Player player) {
        return playerFrames.get(player)
                           .preview();
    }
}