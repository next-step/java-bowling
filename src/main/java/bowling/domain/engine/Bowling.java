package bowling.domain.engine;

import bowling.dto.FramesDto;

public class Bowling {

    private final FrameCreator frameCreator = new FrameCreator();
    private final Frames frames;

    public Bowling() {
        frames = Frames.initialize(frameCreator.create());
    }

    public void throwBall(PitchResult pitchResult) {
        Frame currentFrame = frames.getLast();
        if (currentFrame.isEnded()) {
            currentFrame = goToNextFrame();
        }

        currentFrame.throwBall(pitchResult);
    }

    public int getNextFrameNumber() {
        return frames.getNextFrameNumber();
    }

    private Frame goToNextFrame() {
        Frame nextFrame = frameCreator.create();
        frames.add(nextFrame);

        return nextFrame;
    }

    public boolean isEnded() {
        return frames.isAllFrameEnded();
    }

    public FramesDto exportFrames() {
        return frames.export();
    }

}
