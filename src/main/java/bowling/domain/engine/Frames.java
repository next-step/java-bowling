package bowling.domain.engine;

import bowling.dto.FramesDto;
import bowling.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Frames {

    private static final int TOTAL_FRAMES = 10;

    private final List<Frame> frames = new ArrayList<>();

    private Frames() {}

    public static Frames initialize(Frame firstFrame) {
        Frames frames = new Frames();
        frames.add(firstFrame);

        return frames;
    }

    public void add(Frame frame) {
        frames.add(frame);
    }

    public Frame getLast() {
        return ListUtils.getLastElement(frames);
    }

    public int getNextFrameNumber() {
        int nextFrameNumber = frames.size() + (getLast().isEnded() ? 1 : 0);

        return nextFrameNumber > TOTAL_FRAMES ? -1 : nextFrameNumber;
    }

    public boolean isAllFrameEnded() {
        return frames.size() == TOTAL_FRAMES && getLast().isEnded();
    }

    public FramesDto export() {
        return frames.stream()
                     .map(Frame::export)
                     .collect(collectingAndThen(toList(), FramesDto::new));
    }

}
