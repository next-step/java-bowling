package bowling.domain.frame;

import java.util.LinkedList;
import java.util.List;

public class Board {

    private final List<Frame> frames;

    public Board() {
        this.frames = new LinkedList<>();
        frames.add(new NormalFrame());
    }

    public int getCurrentFrame() {
        return frames.size();
    }
}
