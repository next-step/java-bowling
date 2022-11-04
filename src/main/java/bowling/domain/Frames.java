package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private final List<Frame> values;

    public Frames(List<Frame> values) {
        this.values = values;
    }

    public static Frames init() {
        List<Frame> values = new ArrayList<>();
        values.add(new NormalFrame(1));

        return new Frames(values);
    }

    public void addFrame() {
        try {
            values.add(lastFrame().nextFrame);
        } catch (IllegalStateException e) {
            return;
        }
    }

    public Frame lastFrame() {
        return values.get(values.size() - 1);
    }

    public boolean isLast() {
        return !lastFrame().canBowl();
    }

    public List<Frame> values() {
        return Collections.unmodifiableList(values);
    }
}
