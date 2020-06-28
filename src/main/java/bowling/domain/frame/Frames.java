package bowling.domain.frame;

import bowling.domain.score.FrameScores;
import bowling.domain.score.PitchScore;

import java.util.ArrayList;
import java.util.Collections;
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

    public void bowl(PitchScore pitchScore) {
        getCurrentFrame().bowl(pitchScore);
    }

    private Frame getCurrentFrame() {
        return frames.get(frames.size() - INDEX_CONSTANT);
    }

    public void moveToNextFrame() {
        Frame currentFrame = getCurrentFrame();
        if (currentFrame.isFinished() && frames.size() != 10) {
            Frame nextFrame = currentFrame.next(getCurrentIndex());
            frames.add(nextFrame);
        }
    }

    public boolean hasNextTurn() {
        return !(frames.size() == 10 && frames.get(9).isFinished());
    }

    public int getCurrentIndex() {
        return frames.size();
    }

    public FrameScores getFrameScores() {
        return FrameScores.of(getFrames());
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
