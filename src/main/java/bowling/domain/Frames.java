package bowling.domain;

import java.util.LinkedList;

public class Frames {

    private static final int FRAME_START_INDEX = 1;
    public static final int MAX_FRAME_COUNT = 10;

    private LinkedList<Frame> frames;
    private PitchStrategy pitchStrategy;

    private Frames(PitchStrategy pitchStrategy){
        this.frames = new LinkedList<>();
        this.pitchStrategy = pitchStrategy;
    }

    public static Frames of(PitchStrategy pitchStrategy) {
        return new Frames(pitchStrategy);
    }

    public LinkedList<Frame> getFrames() {
        return frames;
    }

    public boolean isEnd() {
        return frames.size() == MAX_FRAME_COUNT && frames.getLast().isEnd();
    }

    public void execute() {

        if(frames.isEmpty()){
            makeFirstFrame();
        }

        Frame currentFrame = frames.getLast();
        currentFrame.start(pitchStrategy);

    }

    private int getCurrentFrameIndex() {
        return frames.size();
    }

    private void makeFirstFrame() {
        frames.add(NormalFrame.from(FRAME_START_INDEX));
    }

    public void makeNextFrames() {
        if(frames.getLast().isEnd() && frames.size() < MAX_FRAME_COUNT){
            Frame nextFrame = frames.getLast().makeNextFrame(getCurrentFrameIndex());
            frames.add(nextFrame);
        }
    }
}

