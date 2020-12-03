package step2.domain.dto;

import step2.domain.Frames;
import step2.domain.Player;

public class PlayerDTO {
    Player player;
    Frames frames;

    public PlayerDTO(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public Player getPlayer() {
        return player;
    }

    public Frames getFrames() {
        return frames;
    }
}
