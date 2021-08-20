package bowling;

import bowling.frame.Frames;
import bowling.player.Player;

public class BowlingFacade {
    private Player player;
    private Frames frames;

    private BowlingFacade(final Player player, final Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public static BowlingFacade of(final Player player, final Frames frames) {
        return new BowlingFacade(player, frames);
    }
}
