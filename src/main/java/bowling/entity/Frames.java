package bowling.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Frames {

    private static final int MAXIMUM_FRAME_COUNT = 12;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public static Frames initialize() {
        List<Frame> frames = new ArrayList<>();
        for (int frameNumber = 1; frameNumber <= 10; frameNumber++) {
            frames.add(new Frame(frameNumber));
        }
        return new Frames(frames);
    }

    public void moreFrame() {
        if (canAddFrame()) {
            frames.add(new Frame(getFrameCount() + 1));
        }
    }

    private boolean canAddFrame() {
        return frames.size() < MAXIMUM_FRAME_COUNT;
    }

    private int getFrameCount() {
        return frames.size();
    }

    public Frame getCurrentFrame() {
        return frames.stream()
                .filter(Frame::isNotFinished)
                .findFirst()
                .orElseGet(() -> frames.get(frames.size() - 1));
    }

    public int getCurrentFrameNumber() {
        return getCurrentFrame().getNumber();
    }

    public Optional<Frame> getFrame(int frameNumber) {
        try {
            return Optional.of(frames.get(frameNumber - 1));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public boolean hasNotFinishedFrame() {
        return frames.stream()
                .anyMatch(Frame::isNotFinished);
    }

    public int getTotalFrameCount() {
        return frames.size();
    }

}
