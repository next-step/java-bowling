package bowling.domain.frame;

import bowling.domain.score.FrameNumericScore;
import bowling.domain.score.FrameNumericScores;
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
            frames.add(currentFrame.next());
        }
    }

    public boolean hasNextTurn() {
        return getCurrentIndex() != 11;
    }

    public int getCurrentIndex() {
        return getCurrentFrame().getIndex();
    }

    private Frame getCurrentFrame() {
        return frames.get(frames.size() - INDEX_CONSTANT);
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public List<Integer> getFrameScores() {
        FrameNumericScores frameNumericScores = new FrameNumericScores();
        int frameSize = frames.size();
        for (int i = 0; i < frameSize - 1; i++) {
            Frame lastFrame = frames.get(i);
            Frame nextFrame = frames.get(i + 1);
            FrameNumericScore frameNumericScore = nextFrame.calculateFrameScore(lastFrame);
            frameNumericScores.add(frameNumericScore);
        }
        return frameNumericScores.getScores();
    }
}
