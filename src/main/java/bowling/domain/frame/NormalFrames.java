package bowling.domain.frame;

import bowling.domain.BallThrowable;
import bowling.domain.Endable;

import java.util.ArrayList;
import java.util.List;

public class NormalFrames implements BallThrowable, Endable {

    private static final int LAST_FRAME = 9;

    private List<Frame> frames;

    public NormalFrames() {
        this.frames = new ArrayList<>();
        frames.add(new Frame());
    }

    @Override
    public boolean ended() {
        return this.frames.size() == LAST_FRAME && this.frames.get(LAST_FRAME - 1).ended();
    }

    @Override
    public void throwBall(int point) {
        int lastIndex = frames.size() - 1;
        if (!frames.get(lastIndex).ended()) {
            frames.get(lastIndex).throwBall(point);
        }
        if (frames.get(lastIndex).ended() && frames.size() < LAST_FRAME) {
            frames.add(new Frame());
        }
    }

    public int frameCount() {
        return frames.size();
    }

    public List<Frame> frames() {
        return frames;
    }
}
