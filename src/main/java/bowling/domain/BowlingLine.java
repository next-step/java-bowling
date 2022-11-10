package bowling.domain;

public class BowlingLine {
    private Bowling bowling;
    private BowlingStrategy bowlingStrategy;

    public BowlingLine(Bowling bowling, BowlingStrategy bowlingStrategy) {
        this.bowling = bowling;
        this.bowlingStrategy = bowlingStrategy;
    }

    public boolean availablePitching(Frame frame) {
        if (frame.isBonusFrame()) {
            return !bowling.hasRemainPin(Frame.FRAME_10_2);
        }
        return bowling.hasRemainPin(frame);
    }

    public int pitching(Frame frame) {
        return bowling.pitching(frame, bowlingStrategy);
    }

    public String score(Frame frame) {
        return bowling.mark(frame);
    }

    public boolean isStrike(Frame frame) {
        return bowling.isStrike(frame.beforeFrame());
    }

    public String userName() {
        return bowling.userName();
    }
}
