package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import static bowling.utils.BowlingConstants.MAX_PLAYER_LENGTH;

public class Player {

    private String name;
    private Frames frames;

    public Player(String name) {
        if(name.length() > MAX_PLAYER_LENGTH) {
            throw new IllegalArgumentException("사용자의 이름은 3글자를 넘길 수 없음");
        }
        this.name = name;
        this.frames = new Frames();
    }

    public Frame rollBall(Pin pin, Frame frame) {
        int frameNumber = frame.getCurrFrame();
        this.frames.addToFrames(frameNumber, frame);
        frame = frame.rollBowlingBall(pin);

        return frame;
    }

    public Frame findFrameByFrameNumber(int frame) {
        return this.frames.findFrameByFrameNumber(frame);
    }

    public boolean isPlayerFinished() {
        if(frames.isMaxFrameCount()
                && frames.isLastFrameFinished()) {
            return true;
        }
        return false;
    }

    public String getName() {
        return this.name;
    }
}
