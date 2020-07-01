package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    public static final int BOWLING_GAME_FRAME = 9;

    private List<Frame> frames = new ArrayList<>();

    public Frames() {
        frames.add(new NormalFrame());
    }

    public void addFrame(Frame frame) {
        frames.add(frame);
    }

    public int addFrameScore(int frameCount, int score) {
        return frames.get(frameCount).addScore(score, this);
    }

    public int getFrameScore(int frameCount) {
        return frames.get(frameCount).getScore(this);
    }

    public int getFrameSize() {
        return frames.size();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
