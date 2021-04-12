package bowling.domain;

public class BowlingNormalFrame extends BowlingFrame {

    private final Score score;

    public BowlingNormalFrame(Round round, Score score) {
        super(round);
        this.score = score;
    }

    public static BowlingNormalFrame of(int round, Point point) {
        return new BowlingNormalFrame(Round.of(round), Score.first(point));
    }

    public static BowlingNormalFrame of(Round round) {
        return new BowlingNormalFrame(round, Score.of(Point.of(0), Point.of(0)));
    }

    @Override
    public BowlingFrame secondPitching(Point point) {
        return new BowlingNormalFrame(getRound(), score.next(point));
    }

    @Override
    public BowlingFrame firstPitching(Point point) {
        return new BowlingNormalFrame(getRound(), Score.first(point));
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
