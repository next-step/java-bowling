package refactor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Frames {
    private final List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames create() {
        List<Frame> frames = Stream.iterate(new Frame(), frame -> frame)
                .limit(9)
                .collect(Collectors.toList());
        frames.add(new Frame(0, 3));
        return new Frames(frames);
    }

    public void add(Frame frame) {
        this.frames.add(frame);
    }

    public List<Frame> frames() {
        return this.frames;
    }

    public Frame get(int i) {
        return this.frames.get(i);
    }

    public void play(int i) {
        Frame frame = this.frames.get(i);
        frame = frame.pitch();
        this.frames.set(i, frame);
    }
}
