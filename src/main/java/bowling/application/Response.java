package bowling.application;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.LinkedList;
import java.util.List;

public class Response {

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
        return frames.getFrameNumber();
    }
}
