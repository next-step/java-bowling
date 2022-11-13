package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private final List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
        frames.add(new Frame());
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public void addScore(int index, Score score) {
        frames.get(index).addScore(score);
    }
}
