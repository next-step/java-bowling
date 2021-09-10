package bowling.model;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames implements Iterable<Frame> {
    private static final int NORMAL_FRAME_SIZE = 9;

    private final List<Frame> frames;

    public Frames() {
        List<Frame> frames = IntStream.range(0, NORMAL_FRAME_SIZE).mapToObj(i -> new NormalFrame()).collect(Collectors.toList());
        frames.add(new FinalFrame());
        this.frames = frames;
    }

    private Frame currentPlayingFrame() {
        return frames.stream().filter(frame -> !frame.isOver()).findFirst().orElse(null);
    }

    public void record(ShotResult shotResult) {
        currentPlayingFrame().record(shotResult);
    }

    public boolean isOver() {
        return frames.stream().allMatch(Frame::isOver);
    }

    public int currentPlayingFrameIndex() {
        return frames.indexOf(currentPlayingFrame());
    }

    @Override
    public Iterator<Frame> iterator() {
        return frames.iterator();
    }
}
