package bowling.application;

import bowling.domain.frame.Frame;

import java.util.LinkedList;
import java.util.List;

public class Response {

    private List<Frame> frames;

    public Response(List<Frame> frames) {
        this.frames = frames;
    }

    public List<Frame> getFrames() {
        return new LinkedList<>(frames);
    }
}
