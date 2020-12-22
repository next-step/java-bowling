package bowling.domain;

import java.util.*;

public class Frames {

    private static final int FRAME_START_INDEX = 1;
    public static final int MAX_FRAME_COUNT = 10;

    private List<Frame> frames;
    private PitchStrategy pitchStrategy;

    private Frames(PitchStrategy pitchStrategy){
        this.frames = new ArrayList<>();
        this.pitchStrategy = pitchStrategy;
    }

    public static Frames of(PitchStrategy pitchStrategy) {
        return new Frames(pitchStrategy);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public boolean isEnd() {
        return frames.size() == MAX_FRAME_COUNT && this.getLast().isEnd();
    }

    public void execute() {

        if (frames.isEmpty()) {
            makeFirstFrame();
        }

        Frame currentFrame = this.getLast();
        currentFrame.start(pitchStrategy);

    }

    private int getCurrentFrameIndex() {
        return frames.size();
    }

    private void makeFirstFrame() {
        frames.add(NormalFrame.from(FRAME_START_INDEX));
    }

    public void makeNextFrames() {
        if (this.getLast().isEnd() && frames.size() < MAX_FRAME_COUNT) {
            Frame nextFrame = this.getLast().makeNextFrame(getCurrentFrameIndex());
            frames.add(nextFrame);
        }
    }

    public Frame getLast(){
        return frames.stream()
                .reduce((first, second) -> second)
                .orElseThrow(NoSuchElementException::new);
    }
}

