package bowling.domain;

import java.util.LinkedList;
import java.util.List;

class Frames {
    private final List<Frame> frames = new LinkedList<>();

    void add(Frame frame) {
        frames.add(frame);
    }

    List<Integer> scores() {
        List<Integer> scores = new LinkedList<>();
        return scores;
    }
}
