package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Frames implements Iterable<Frame> {

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames create() {
        return new Frames(new ArrayList<>());
    }

    public void addFrame(Frame frame) {
        frames.add(frame);
    }

    public int size() {
        return frames.size();
    }

    public Frame get(int i) {
        return frames.get(i);
    }

    public boolean isGameEnd() {
        Frame lastFrame = frames.get(frames.size() - 1);

        if (lastFrame instanceof FinalFrame) {
            return !lastFrame.hasNextRound();
        }

        return false;
    }

    @Override
    public Iterator<Frame> iterator() {
        return frames.iterator();
    }

    @Override
    public void forEach(Consumer<? super Frame> action) {
        frames.forEach(action);
    }

}
