package bowling.application;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.Collections;
import java.util.List;

public class Response {

    private final Frames frames;

    public Response(Frames frames) {
        this.frames = frames;
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames.getFramesByCalculationScore());
    }

    public String getName() {
        return frames.getName();
    }

    public int getFrameNumber() {
        return frames.getFrameNumber();
    }

    public boolean isLastFrame() {
        return frames.isEnd();
    }
}
