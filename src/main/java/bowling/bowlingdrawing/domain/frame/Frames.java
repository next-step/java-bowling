package bowling.bowlingdrawing.domain.frame;

import bowling.bowlingdrawing.domain.pitching.Pitching;
import bowling.bowlingdrawing.exception.CustomException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private final List<Frame> frames = new ArrayList<>();

    public void pitch(Pitching pitching) {
        validateGameEnd();
        validateOverTenFrames();

        if (frames.isEmpty()) {
            addNewFrame(pitching);
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

    private void validateOverTenFrames() {
        if (frames.size() == 10) {
            throw new CustomException("최대 Frame(10) 초과");
        }
    }

    private void addNewFrame(Pitching pitching) {
        Frame firstFrame = new Frame(pitching);
        frames.add(firstFrame);
    }

    public boolean end() {
        return frames.size() == 10 && frames.get(frames.size()-1).end();
    }

    public int currentFrame() {
        if (frames.isEmpty()) {
            return 1;
        }

        if (frames.size() == 10) {
            return frames.size();
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

    public boolean fullFrame() {
        return frames.size() == 10;
    }
}
