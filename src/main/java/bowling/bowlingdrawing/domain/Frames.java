package bowling.bowlingdrawing.domain;

import bowling.bowlingdrawing.exception.CustomException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private final List<Frame> frames = new ArrayList<>();

    public void pitch(int pins) {
        if (frames.isEmpty()) {
            frames.add(Frame.first(pins));
            return;
        }

        validateEndOfFrames();

        Frame currentFrame = currentFrame();
        if (currentFrame.done()) {
            frames.add(currentFrame.next(pins));
            return;
        }

        currentFrame.pitching(pins);
    }

    private void validateEndOfFrames() {
        if (currentFrame().done() && frames.size() == 10) {
            throw new CustomException("10 Frame 게임이 종료 되었습니다.");
        }
    }

    public List<Frame> frames() {
        return Collections.unmodifiableList(frames);
    }

    public int nextPitchingFrameNumber() {
        if (currentFrame().done()) {
            return frames.size() + 1;
        }
        return frames.size();
    }

    private Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }
}
