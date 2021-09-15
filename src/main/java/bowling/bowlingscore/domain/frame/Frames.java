package bowling.bowlingscore.domain.frame;

import bowling.bowlingscore.domain.pitching.Pitching;
import bowling.bowlingscore.exception.CustomException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Frames {

    public static final int MAXIMUM_SIZE_OF_FRAMES = 10;
    public static final int BEFORE_MAXIMUM_SIZE_OF_FRAMES = 9;
    public static final int MAXIMUM_INDEX_OF_FRAMES = 9;

    private final List<Frame> frames = new ArrayList<>();
    private Pitching currentPitching;

    public void pitch(int pins) {
        validateGameEnd();
        currentPitching = changeCurrentPitching(pins);

        if (frames.isEmpty()) {
            addNormalFrame(currentPitching);
            return;
        }

        Frame currentFrame = frames.get(frames.size() - 1);
        if (frames.size() == BEFORE_MAXIMUM_SIZE_OF_FRAMES && currentFrame.done()) {
            addFinalFrame(currentPitching);
            return;
        }

        if (currentFrame.done()) {
            addNormalFrame(currentPitching);
            return;
        }

        currentFrame.pitch(currentPitching);
    }

    private Pitching changeCurrentPitching(int pins) {
        if (currentPitching == null) {
            return Pitching.first(pins);
        }
        return currentPitching.next(pins);
    }

    private void validateGameEnd() {
        if (end()) {
            throw new CustomException("이미 게임이 끝났습니다.");
        }
    }

    public boolean end() {
        return frames.size() == MAXIMUM_SIZE_OF_FRAMES &&
                frames.get(MAXIMUM_INDEX_OF_FRAMES).done();
    }

    private void addNormalFrame(Pitching pitching) {
        Frame firstFrame = new Frame(pitching);
        frames.add(firstFrame);
    }

    private void addFinalFrame(Pitching pitching) {
        Frame finalFrame = new FinalFrame(pitching);
        frames.add(finalFrame);
    }

    public int currentFrameNumber() {
        if (frames.isEmpty()) {
            return 1;
        }

        Frame currentFrame = frames.get(frames.size() - 1);
        if (currentFrame.done()) {
            return frames.size() + 1;
        }
        return frames.size();
    }

    public List<Frame> frames() {
        return Collections.unmodifiableList(frames);
    }

    public FinalFrame finalFrame() {
        if (frames.size() != MAXIMUM_SIZE_OF_FRAMES) {
            return null;
        }
        return (FinalFrame) frames.get(MAXIMUM_INDEX_OF_FRAMES);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Frames)) return false;
        Frames frames1 = (Frames) o;
        return Objects.equals(frames, frames1.frames) && Objects.equals(currentPitching, frames1.currentPitching);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames, currentPitching);
    }
}
