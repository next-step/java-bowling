package bowling.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Frames implements Iterable<Frame> {

    private final List<Frame> frames;

    public Frames() {
        this(new ArrayList<>());
    }

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public void pitch(int pinDownCount) {
        if (isEmpty()) {
            start();
        }
        if (lastFrame().isFinished()) {
            addNextFrame();
        }
        lastFrame().pitch(new Pitch(pinDownCount));
    }

    public int lastFrameNumber() {
        return lastFrame().number();
    }

    public boolean isFinished() {
        return lastFrame() instanceof FinalFrame && lastFrame().isFinished();
    }

    private boolean isEmpty() {
        return frames.size() == 0;
    }

    private void start() {
        frames.add(NormalFrame.first());
    }

    private void addNextFrame() {
        frames.add(lastFrame().next());
    }

    private Frame lastFrame() {
        return frames.get(frames.size() - 1);
    }

    @Override
    public Iterator<Frame> iterator() {
        return frames.iterator();
    }

    @Override
    public void forEach(Consumer<? super Frame> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Frame> spliterator() {
        return Iterable.super.spliterator();
    }
}
