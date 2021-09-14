package bowling.bowlingdrawing.domain.frame;

import bowling.bowlingdrawing.domain.pitching.Pitching;
import bowling.bowlingdrawing.exception.CustomException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private final List<Frame> frames = new ArrayList<>();
    private FinalFrame finalFrame;

    public void pitch(Pitching pitching) {
        validateGameEnd();

        if (frames.isEmpty()) {
            addNewFrame(pitching);
            return;
        }

        if (frames.size() == 9) {
            pitchAtFinalFrame(pitching);
            return;
        }

        Frame currentFrame = frames.get(frames.size() - 1);
        if (currentFrame.done()) {
            addNewFrame(pitching);
            return;
        }

        currentFrame.secondPitching(pitching);
    }

    private void validateGameEnd() {
        if (end()) {
            throw new CustomException("이미 게임이 끝났습니다.");
        }
    }

    public boolean end() {
        return finalFrame != null && finalFrame.end();
    }

    private void addNewFrame(Pitching pitching) {
        Frame firstFrame = new Frame(pitching);
        frames.add(firstFrame);
    }

    private void pitchAtFinalFrame(Pitching pitching) {
        if (finalFrame != null) {
            finalFrame.pitch(pitching);
            return;
        }
        finalFrame = new FinalFrame(pitching);
    }

    public int currentFrame() {
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
        return finalFrame;
    }
}
