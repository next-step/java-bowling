package bowling.domain;

import bowling.domain.exception.InvalidNameException;
import bowling.domain.frame.BowlingFrames;
import bowling.domain.frame.Frame;
import bowling.domain.pin.FallenPins;
import java.util.regex.Pattern;

public class Player {

    private static final String NAME_REGX = "^[a-zA-Z]{3}$";

    private final String name;
    private final BowlingFrames frames;

    public Player(String name) {
        validateName(name);
        this.name = name.toUpperCase();
        this.frames = new BowlingFrames();
    }

    public String getName() {
        return this.name;
    }

    public BowlingFrames getFrames() {
        return frames;
    }

    public void bowlBall(int indexOfFrame, FallenPins fallenPins) {
        frames.getFrame(indexOfFrame)
                .updateFrameState(fallenPins);
    }

    public boolean isFinishedBowling(int indexOfFrame) {
        Frame frame = frames.getFrame(indexOfFrame);
        return frame.isFinish();
    }

    private void validateName(String name) {
        if (!Pattern.matches(NAME_REGX, name)) {
            throw new InvalidNameException();
        }
    }
}
