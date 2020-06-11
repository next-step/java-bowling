package bowling.step2.domain;

import java.util.Map;

public class PlayerFrames {
    private final Map<PlayerName, Frames> playerFrames;

    private PlayerFrames (Map<PlayerName, Frames> playerFrames) {
        this.playerFrames = playerFrames;
    }
}