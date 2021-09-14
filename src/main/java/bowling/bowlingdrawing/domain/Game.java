package bowling.bowlingdrawing.domain;

import bowling.bowlingdrawing.domain.frame.Frames;
import bowling.bowlingdrawing.domain.pitching.Pitching;
import bowling.bowlingdrawing.domain.pitching.Pitchings;

public class Game {
    private final Frames frames = new Frames();
    private final Pitchings pitchings = new Pitchings();

    public void pitch(int pins) {
        Pitching nextPitching = pitchings.nextPitching(pins);
        frames.pitch(nextPitching);
    }

    public Frames frames() {
        return frames;
    }

    public Pitchings pitchings() {
        return pitchings;
    }

    public boolean end() {
        return frames.end();
    }

    public int currentFrame() {
        return frames.currentFrame();
    }
}
