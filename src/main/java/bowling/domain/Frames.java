package bowling.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int FRAME_NUMBER = 10;

    @Getter
    private List<Frame> frames = new ArrayList<>();

    public void play(int clearPinCount) {
        if (frames.isEmpty()) {
            frames.add(Frame.fistFrame(clearPinCount));
            return;
        }

        Frame frame = lastFrame();

        if (frame.isEndFrame()) {
            frame = frame.next(clearPinCount);
            frames.add(frame);

            return;
        }

        frame.roundPlay(clearPinCount);
    }

    public boolean isEnd() {
        if(frames.size() >= FRAME_NUMBER) {
            return lastFrame().isEndFrame();
        }

        return false;
    }

    public int getFrameNumber() {
        return frames.size();
    }

    private Frame lastFrame() {
        return this.frames.get(this.frames.size() - 1);
    }
}
