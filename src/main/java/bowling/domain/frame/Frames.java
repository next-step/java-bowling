package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public int nextFrame() {
        return 0;
    }

    public Frames addScore(int score) {
        return new Frames(new ArrayList<>());
    }

}
