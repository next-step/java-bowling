package bowling.frame;

import bowling.score.Score;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames initiate() {
        List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.initiate());
        return new Frames(frames);
    }

    public void bowl(Score score) {
        frames.get(frames.size() - 1).bowl(score);
    }

    public void moveToNextFrame() {
        Frame currentFrame = frames.get(frames.size() - 1);
        if (currentFrame.isMovableToNextFrame()) {
            frames.add(currentFrame.next());
        }
    }

    public boolean hasNextTurn() {
        return frames.get(frames.size() - 1) != null;
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
