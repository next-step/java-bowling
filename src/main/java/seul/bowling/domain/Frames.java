package seul.bowling.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int ONE = 1;
    private static final int ZERO = 0;

    private int scoreEndIndex;
    @Getter
    private List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
    }

    public void play(int clearPin) {
        addBonusScore(clearPin);

        Frame frame = playFrame();
        frame.addPins(clearPin);

        calculateCumulativeScore();
    }

    private void calculateCumulativeScore() {
        if (frames.isEmpty()) {
            return;
        }

        Frame frame = getFrame(scoreEndIndex);
        if (frame.endCalculateScore() && scoreEndIndex + ONE < frames.size()) {
            int score = frame.getScore();

            Frame targetFrame = getFrame(scoreEndIndex + ONE);
            targetFrame.addCumulativeScore(score);

            scoreEndIndex++;
        }
    }

    public boolean end() {
        Frame frame = lastFrame();
        if (frame.isLastFame() && frame.endFrame()) {
            return true;
        }
        return false;
    }

    public int currentFrameIndex() {
        if (isEmpty())
            return ZERO;

        return frames.get(frames.size() - ONE).getIndex();
    }

    public Frame getFrame(int index) {
        return frames.get(index);
    }

    public boolean isEmpty() {
        return frames.isEmpty();
    }

    private Frame playFrame() {
        if (isEmpty()) {
            Frame frame = Frame.first();
            frames.add(frame);
            return frame;
        }

        Frame frame = lastFrame();
        if (frame.endFrame()) {
            frame = frame.next();

            frames.add(frame);
        }

        return frame;
    }

    private void addBonusScore(int bonusScore) {
        frames.stream()
                .filter(frame -> frame.availableAddBonusScore())
                .forEach(frame -> frame.addBonusScore(bonusScore));
    }

    private Frame lastFrame() {
        return frames.get(frames.size() - ONE);
    }
}
