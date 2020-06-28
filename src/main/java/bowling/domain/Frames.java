package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    List<Frame> frames = new ArrayList<>();

    public boolean add(Frame frame) {
        return frames.add(frame);
    }

    public List<String> showGameResult() {
        List<String> result = new ArrayList<>();
        frames.forEach(frame -> {
            result.add(frame.showResult());
        });
        return result;
    }

    public State checkLastFrameState() {
        return frames.get(frames.size()-1).state;
    }
}
