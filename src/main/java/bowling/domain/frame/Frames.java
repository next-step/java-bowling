package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static List<Frame> frames = new ArrayList<>();

    public static void addToFrames(Frame frame) {
        frames.add(frame);
    }

    public static List<Frame> getFrames() {
        return frames;
    }
}
