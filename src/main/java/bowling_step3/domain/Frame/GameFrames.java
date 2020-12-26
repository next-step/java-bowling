package bowling_step3.domain.Frame;

import bowling_step3.exception.PitchOverBoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameFrames {
    public static final int NORMAL_MAX_FRAME = 9;

    public static final int MAX_FRAME_COUNT = 10;

    private final List<Frame> frames;

    public GameFrames() {
        this.frames = new ArrayList<>();
    }

    private Frame getLastFrame() {
        if (frames.isEmpty()) {
            throw new PitchOverBoundException();
        }

        return frames.get(frames.size() - 1);
    }

    public void pitch(int count) {
        if (isFinish()) {
            throw new PitchOverBoundException();
        }

        this.makeFrame();
        getLastFrame().pitch(count);
        calculateScore(count);
    }

    private void calculateScore(int count) {
        frames.forEach(frame -> {
            int index = frames.size() - 1;
            frame.calculateScore(index, count);
        });
    }

    private void makeFrame() {
        if (frames.isEmpty()) {
            frames.add(NormalFrame.init());
            return;
        }

        if (!getLastFrame().isFinish()) {
            return;
        }

        frames.add(frames.size() == NORMAL_MAX_FRAME ? new FinalFrame() : getLastFrame().next());
    }


    private boolean isFinalFrame() {
        return frames.size() == MAX_FRAME_COUNT;
    }

    public boolean isFinish() {
        return isFinalFrame() && getLastFrame().isFinish();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public int getIndex() {
        if (frames.isEmpty() || getLastFrame().isFinish()) {
            return frames.size() + 1;
        }

        return frames.size();
    }

    public List<Integer> getScores() {
        return frames.stream()
                .filter(Frame::hasScore)
                .map(Frame::getScore)
                .collect(Collectors.toList());
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
