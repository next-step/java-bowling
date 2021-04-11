package bowling.domain;

public class BowlingNormalFrame implements BowlingFrame {

    private final Round round;
    private final Score score;

    public BowlingNormalFrame(Round round, Score score) {
        this.round = round;
        this.score = score;
    }

    public static BowlingNormalFrame first() {
        return new BowlingNormalFrame(Round.of(0), Score.of(Point.of(0), Point.of(0)));
    }

    public static BowlingNormalFrame of(int position, Point point) {
        return new BowlingNormalFrame(Round.of(position), Score.first(point));
    }

    public static BowlingNormalFrame of(Round round) {
        return new BowlingNormalFrame(round, Score.of(Point.of(0), Point.of(0)));
    }

    @Override
    public BowlingFrame secondPitching(Point point) {
        return new BowlingNormalFrame(round, score.next(point));
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

    @Override
    public BowlingFrame nextFrame() {
        if (round.isFinal()) {
            return BowlingFinalFrame.of(round.next());
        }
        return BowlingNormalFrame.of(round.next());
    }


}
