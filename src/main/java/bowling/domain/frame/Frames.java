package bowling.domain.frame;

import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int INDEX_CONSTANT = 1;

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
        getCurrentFrame().bowl(score);
    }

    public void moveToNextFrame() {
        Frame currentFrame = getCurrentFrame();
        if (currentFrame.isMovableToNextFrame()) {
            Frame nextFrame = currentFrame.next(getCurrentIndex());
            frames.add(nextFrame);//변경
        }
    }

    public boolean hasNextTurn() {
        return !frames.contains(null);
    }

    public int getCurrentIndex() {
        return frames.size();
    }

    private Frame getCurrentFrame() {
        return frames.get(frames.size() - INDEX_CONSTANT);
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public List<Integer> getFrameScores() {
        return null;
    }
}
