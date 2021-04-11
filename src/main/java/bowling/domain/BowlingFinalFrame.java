package bowling.domain;

public class BowlingFinalFrame implements BowlingFrame {

    private final Round round;
    private final Score score;

    private BowlingFinalFrame(Round round, Score score) {
        this.round = round;
        this.score = score;
    }

    public static BowlingFinalFrame of(Score score) {
        return new BowlingFinalFrame(Round.of(10), score);
    }

    public static BowlingFrame of(Round round, Score score) {
        return new BowlingFinalFrame(round, score);
    }

    public static BowlingFrame of(Round round) {
        return new BowlingFinalFrame(round, Score.of(Point.of(0), Point.of(0)));
    }

    @Override
    public Round position() {
        return round;
    }

    @Override
    public BowlingFrame second(Point point) {
        return new BowlingNormalFrame(round, score.next(point));
    }

    public boolean isOneMoreTime() {
        return score.score() != BowlingRole.MISS;
    }
}
