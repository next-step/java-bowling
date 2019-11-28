package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Bowling {

    private List<Frame> frames;

    public Bowling() {
        this.frames = new ArrayList<>();
    }

    public void go(int countOfHit) {
        frames.add(new Frame(countOfHit, 1));
    }

    public boolean isContains(Frame frame) {
        return frames.contains(frame);
    }
}
