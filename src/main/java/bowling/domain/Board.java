package bowling.domain;
import java.util.ArrayList;
import java.util.List;
public class Board {
    private List<Frame> frames = new ArrayList<>();

    public void add(Frame frame) {
        frames.add(frame);
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
