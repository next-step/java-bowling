package bowling.dto;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

import java.util.List;

public class BowlingResponse {
    private final Frames frames;
    private final Player player;

    public BowlingResponse(final Frames frames, final Player player) {
        this.frames = frames;
        this.player = player;
    }

    public boolean isFinish() {
        return frames.isFinish();
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    public String player(){
        return player.print();
    }

    public int nextFrame() {
        Frame lastFrame = frames.lastFrame();
        if (lastFrame.isFinished()) {
            return lastFrame.number() + 1;
        }
        return lastFrame.number();
    }
}
