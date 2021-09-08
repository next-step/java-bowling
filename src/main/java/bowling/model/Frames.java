package bowling.model;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames implements Iterable<NormalFrame> {
    private static final int FRAME_SIZE = 10;

    private final List<NormalFrame> frames = IntStream.range(0, FRAME_SIZE).mapToObj(i -> new NormalFrame()).collect(Collectors.toList());

    private NormalFrame currentPlayingFrame() {
        return frames.stream().filter(frame -> !frame.isOver()).findFirst().orElse(null);
    }

    public void record(ShotResult shotResult) {
        currentPlayingFrame().record(shotResult);
    }

    public boolean isOver() {
        return frames.stream().allMatch(NormalFrame::isOver);
    }

    public int currentPlayingFrameIndex() {
        return frames.indexOf(currentPlayingFrame());
    }

    @Override
    public Iterator<NormalFrame> iterator() {
        return frames.iterator();
    }
}
