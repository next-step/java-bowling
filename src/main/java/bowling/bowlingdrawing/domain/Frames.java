package bowling.bowlingdrawing.domain;

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
        if (frames.size() == 10 && frames.get(frames.size()-1).end()) {
            throw new CustomException("이미 게임이 끝났습니다.");
        }
    }

    private void validateOverTenFrames() {
        if (frames.size() == 10) {
            throw new CustomException("최대 Frame(10) 초과");
        }
    }

    private void addNewFrame(Pitching pitching) {
        Frame firstFrame = Frame.of(pitching);
        frames.add(firstFrame);
    }

    public List<Frame> frames() {
        return Collections.unmodifiableList(frames);
    }
}
