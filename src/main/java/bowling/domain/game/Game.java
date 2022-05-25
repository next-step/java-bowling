package bowling.domain.game;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

public class Game {
    private final Player player;
    private final Frames frames;

    public Game(Player player, Frames frames) {
        validate(player, frames);
        this.player = player;
        this.frames = frames;
    }

    private void validate(Player player, Frames frames) {
        if (player == null) {
            throw new IllegalArgumentException("player는 null 일 수 없습니다.");
        }
        if (frames == null) {
            throw new IllegalArgumentException("frames는 null 일 수 없습니다.");
        }
    }
}
