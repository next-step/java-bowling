package bowling.domain;

import bowling.domain.frame.FrameNumber;
import bowling.domain.strategy.BowlingStrategy;

public class BowlingLine {
    private final Bowling bowling;
    private final BowlingStrategy bowlingStrategy;

    public BowlingLine(Bowling bowling, BowlingStrategy bowlingStrategy) {
        this.bowling = bowling;
        this.bowlingStrategy = bowlingStrategy;
    }

    public boolean availablePitching(FrameNumber frame) {
       return bowling.availablePitch(frame);
    }

    public int pitching(FrameNumber frame) {
        return bowling.pitching(frame, bowlingStrategy);
    }

    public String score(FrameNumber frame) {
        return bowling.findScore(frame);
    }

    public String userName() {
        return bowling.userName();
    }
}
