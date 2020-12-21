package bowling.model.player;

import bowling.model.Name;
import bowling.model.Pins;
import bowling.model.frame.FrameResult;
import bowling.model.frame.Frames;

import java.util.Arrays;
import java.util.List;

public class Player {
    private Name name;
    private Frames frames;

    private Player(Name name, Frames frames) {
        this.name = name;
        this.frames = frames;
    }

    public static Player of(Name name, Frames frames) {
        return new Player(name, frames);
    }

    public void bowling(int fallenPins) {
        frames.bowling(Pins.from(fallenPins));
    }

    public boolean isTurnOver() {
        return frames.isNewFrame() || frames.isFinished();
    }

    @Override
    public String toString() {
        return name.toString();
    }

    public boolean isEnd() {
        return frames.isFinished();
    }

    public List<FrameResult> info() {
        return Arrays.asList(frames.result(), frames.getScores());
    }
}
