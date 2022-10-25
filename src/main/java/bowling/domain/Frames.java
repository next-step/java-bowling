package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private final List<Frame> values;
    private int lastFrameNumber = 0;

    public Frames(List<Frame> values) {
        this.values = values;
    }

    public static Frames init() {
        List<Frame> values = new ArrayList<>();
        values.add(new NormalFrame(1));

        return new Frames(values);
    }

    public void addFrame() {
        if (lastFrame() instanceof FinalFrame) {
            return;
        }

        values.add(lastFrame().nextFrame());
    }

    public Frame lastFrame() {
        return values.get(values.size() - 1);
    }

    public boolean isLast() {
        return !lastFrame().canPitch();
    }

    public List<Frame> values() {
        return Collections.unmodifiableList(values);
    }
}
