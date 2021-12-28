package bowling.domain;

import java.util.List;

public class Bowling {
    private final Player player;
    private final Frames frames;

    private Bowling(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public static Bowling create(Player player) {
        return new Bowling(player, Frames.init());
    }

    public void bowl(Ball pins) {
        frames.bowl(pins);
    }

    public String getPlayerName() {
        return player.toString();
    }

    public boolean hasNext() {
        return frames.hasNext();
    }

    public int getCurrentFrameIndex() {
        return frames.getLastFrameIndex();
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }
}
