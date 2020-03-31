package bowling.application;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.LinkedList;
import java.util.List;

public class Response {

    private static final int BONUS_FRAME_NUMBER = 11;
    private static final int FINAL_FRAME_NUMBER = 10;

    private Frames frames;

    public Response(Frames frames) {
        this.frames = frames;
    }

    public List<Frame> getFrames() {
        return new LinkedList<>(frames.getFramesByCalculationScore());
    }

    public String getName() {
        return frames.getName();
    }

    public int getFrameNumber() {
        if (frames.getFrameNumber() == BONUS_FRAME_NUMBER) {
            return FINAL_FRAME_NUMBER;
        }
        return frames.getFrameNumber();
    }

    public boolean isLastFrame() {
        return frames.isEnd();
    }
}
