package bowling.domain;

public class BowlingLine {
    private Bowling bowling;
    private BowlingStrategy bowlingStrategy;

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
        return bowling.score(frame);
    }

    public String userName() {
        return bowling.userName();
    }
}
