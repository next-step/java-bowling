package bowling.domain;

import java.util.List;
import java.util.Objects;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class BowlingTurn {
    private static final int TOTAL_FRAME_SIZE = 10;

    private final Player player;
    private final Frames frames;

    private BowlingTurn(String name) {
        this.player = Player.of(name);
        this.frames = Frames.init();
    }

    public static BowlingTurn of(String name) {
        return new BowlingTurn(name);
    }

    public void play(int pinCount) {
        frames.bowl(pinCount);
    }

    public void next() {
        frames.next();
    }

    public boolean isDone() {
        return this.currentFrameSize() == TOTAL_FRAME_SIZE
            && this.frames.isDone();
    }

    public int currentFrameSize() {
        return frames().size();
    }

    public String player() {
        return this.player.toString();
    }

    public List<Frame> frames() {
        return this.frames.getFrames();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BowlingTurn that = (BowlingTurn)o;
        return Objects.equals(player, that.player) && Objects.equals(frames, that.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, frames);
    }
}
