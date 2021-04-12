package bowling.domain;

public class BowlingFinalFrame extends BowlingFrame {

    private final Score score;

    private BowlingFinalFrame(Round round, Score score) {
        super(round);
        this.score = score;
    }

    public static BowlingFrame of(Round round) {
        return new BowlingFinalFrame(round, Score.of(Point.of(0), Point.of(0)));
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
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isStrike() {
        return false;
    }

}
