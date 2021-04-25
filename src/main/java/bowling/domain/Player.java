package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class Player {
    private final String name;
    private final Frames frames;

    public Player(String name) {
        this.name = name;
        this.frames = Frames.init();
    }

    public void bowl(int value, int round) {
        frames.bowl(value, round - 1);
    }

    public String name() {
        return name;
    }

    public boolean isNthFrameFinished(int round) {
        return nthFrame(round).isFinished();
    }

    public Frame nthFrame(int round) {
        return frames.nthFrame(round - 1);
    }
}
