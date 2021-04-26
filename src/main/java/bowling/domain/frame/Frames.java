package bowling.domain.frame;

import java.util.List;

public class Frames {
    List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public boolean isFinished(int index) {
        return frames.get(index).isFinished();
    }

    
}
