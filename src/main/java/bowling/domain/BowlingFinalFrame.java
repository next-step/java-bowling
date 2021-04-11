package bowling.domain;

public class BowlingFinalFrame implements BowlingFrame {

    private final Round round;
    private final Score score;

    private BowlingFinalFrame(Round round, Score score) {
        this.round = round;
        this.score = score;
    }

    public static BowlingFrame of(Round round) {
        return new BowlingFinalFrame(round, Score.of(Point.of(0), Point.of(0)));
    }

    public static BowlingFrame first(Round round) {
        return new BowlingFinalFrame(round, Score.of(Point.of(0), Point.of(0)));
    }

    public boolean isOneMoreTime() {
        return score.score() != BowlingRole.MISS;
    }

    @Override
    public BowlingFrame secondPitching(Point point) {
        return null;
    }

    @Override
    public BowlingFrame firstPitching(Point point) {
        return null;
    }

    @Override
    public BowlingFrame nextFrame() {
        return null;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isStrike() {
        return false;
    }
}
