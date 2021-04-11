package bowling.domain;

public class BowlingNormalFrame implements BowlingFrame {

    private final Round round;
    private final Score score;

    public BowlingNormalFrame(Round round, Score score) {
        this.round = round;
        this.score = score;
    }

    public static BowlingNormalFrame first(Point point) {
        return new BowlingNormalFrame(Round.of(0), Score.first(point));
    }

    public static BowlingNormalFrame of(int position, Point point) {
        return new BowlingNormalFrame(Round.of(position), Score.first(point));
    }

    public static BowlingFrame of(Round round) {
        return new BowlingNormalFrame(round, Score.of(Point.of(0), Point.of(0)));
    }

    public Round position() {
        return this.round;
    }

    @Override
    public BowlingFrame second(Point point) {
        return new BowlingNormalFrame(round, score.next(point));
    }

    public BowlingFrame nextFrame() {
        if (round.isFinal()) {
            return BowlingFinalFrame.of(round.next());
        }
        return BowlingNormalFrame.of(round.next());
    }


}
