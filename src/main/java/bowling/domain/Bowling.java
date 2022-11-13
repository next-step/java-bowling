package bowling.domain;

import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.Frames;
import bowling.domain.strategy.BowlingStrategy;

public class Bowling {
    private final User user;
    private final Frames frames;

    public Bowling(User user) {
        this.user = user;
        this.frames = new Frames();
    }

    public String userName() {
        return user.name();
    }

    public int pitching(FrameNumber frame, BowlingStrategy bowlingStrategy) {
        return frames.pitch(frame, bowlingStrategy);
    }

    public boolean availablePitch(FrameNumber frame) {
        return frames.availablePitching(frame);
    }

    public String findScore(FrameNumber frame) {
        return frames.score(frame);
    }
}
