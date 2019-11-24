package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private List<Frame> frames;

    public Frames() {
        this.frames = prepareFrames();
    }

    private List<Frame> prepareFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            frames.add(Frame.ready(i));
        }
        return frames;
    }

    public List<Frame> getFrames() {
        return new ArrayList<>(frames);
    }

    public int size() {
        return frames.size();
    }
}
