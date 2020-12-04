package step3.domain.dto;

import step3.domain.Frames;
import step3.domain.Player;

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
