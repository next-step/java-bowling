package bowling.domain.player;

import bowling.domain.frame.Frames;

public class BowlingPlayer {
    private Player player;
    private Frames frames;

    public BowlingPlayer(String name) {
        player = Player.from(name);
        frames = Frames.init();
    }

    public static BowlingPlayer from(String name) {
        return new BowlingPlayer(name);
    }

    public boolean isBowlingEnd() {
        return frames.isBowlingEnd();
    }
}
