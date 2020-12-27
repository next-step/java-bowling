package bowling_step3.domain.Frame;

import bowling_step3.domain.Scores;
import bowling_step3.exception.PitchOverBoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GameFrames {
    public static final int NORMAL_MAX_FRAME = 9;

    public static final int MAX_FRAME_COUNT = 10;

    private final List<Frame> frames = new ArrayList<>();

    public GameFrames() {

    }

    private Frame getLastFrame() {
        return frames.get(size() - 1);
    }

    public void pitch(int count) {
        if (isFinish()) {
            throw new PitchOverBoundException();
        }

        makeFrame();
        getLastFrame().pitch(count);
        calculateScore(count);
    }

    private void calculateScore(int count) {
        frames.forEach(frame -> {
            int index = size() - 1;
            frame.calculateScore(index, count);
        });
    }

    private boolean isNormalMaxFrame() {
        return size() == NORMAL_MAX_FRAME;
    }

    private void addFrames() {
        frames.add(isNormalMaxFrame() ? new FinalFrame() : getLastFrame().next());
    }

    private void makeFrame() {
        if (frames.isEmpty()) {
            frames.add(NormalFrame.init());
            return;
        }

        if (!getLastFrame().isFinish()) {
            return;
        }

        addFrames();
    }


    private boolean isFinalFrame() {
        return size() == MAX_FRAME_COUNT;
    }

    public boolean isFinish() {
        return isFinalFrame() && getLastFrame().isFinish();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public int getIndex() {
        if (frames.isEmpty() || getLastFrame().isFinish()) {
            return size() + 1;
        }

        return size();
    }

    public Scores getScores() {
        Scores scores = Scores.of();
        frames.stream()
                .filter(Frame::hasScore)
                .mapToInt(Frame::getScore)
                .forEach(scores::add);
        return scores;
    }

    public int size() {
        return frames.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameFrames frames1 = (GameFrames) o;
        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }
}
