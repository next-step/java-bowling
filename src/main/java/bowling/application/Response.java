package bowling.application;

import bowling.domain.frame.Bowling;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.state.State;

import java.util.Collections;
import java.util.List;

public class Response {

    private Frames frames;
    private Bowling bowling;

    public Response(Frames frames) {
        this.frames = frames;
    }

    public Response(Bowling bowling) {
        this.bowling = bowling;
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

    public List<State> getState() {
        return Collections.unmodifiableList(bowling.getStates());
    }
}
