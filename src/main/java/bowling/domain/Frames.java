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
        if (isEmpty()) {
            throw new IllegalStateException("프레임이 존재하지 않습니다. 투구를 실행해 주세요.");
        }
        return lastFrame().number();
    }

    public int nextTurnNumber() {
        if (isEmpty()) {
            return NormalFrame.FIRST_NUMBER;
        }
        if (isFinished()) {
            return lastFrameNumber();
        }
        if (lastFrame().isFinished()) {
            return lastFrameNumber() + 1;
        }
        return lastFrameNumber();
    }

    public boolean isFinished() {
        return !isEmpty() && lastFrame() instanceof FinalFrame && lastFrame().isFinished();
    }

    private boolean isEmpty() {
        return frames.isEmpty();
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

    public int totalScore() {
        return frames.stream()
                .mapToInt(Frame::score)
                .sum();
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
