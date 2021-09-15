package bowling.bowlingdrawing.domain;

import bowling.bowlingdrawing.domain.frame.Frames;
import bowling.bowlingdrawing.domain.pitching.Pitching;

import java.util.Objects;

public class Game {
    private final Frames frames = new Frames();
    private Pitching currentPitching;

    public void pitch(int pins) {
        currentPitching = nextPitching(pins);
        frames.pitch(currentPitching);
    }

    public Pitching nextPitching(int pins) {
        if (currentPitching == null) {
            return Pitching.first(pins);
        }
        return currentPitching.next(pins);
    }

    public Frames frames() {
        return frames;
    }

    public boolean end() {
        return frames.end();
    }

    public int currentFrame() {
        return frames.currentFrame();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return Objects.equals(frames, game.frames) && Objects.equals(currentPitching, game.currentPitching);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames, currentPitching);
    }
}
