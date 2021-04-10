package bowling.domain;

public class BowlingFinalFrame implements BowlingFrame {

    private final Position position;
    private final Score score;

    private BowlingFinalFrame(Position position, Score score) {
        this.position = position;
        this.score = score;
    }

    public static BowlingFinalFrame of(Score score) {
        return new BowlingFinalFrame(Position.of(10), score);
    }

    public static BowlingFrame of(Position position, Score score) {
        return new BowlingFinalFrame(position, score);
    }

    public static BowlingFrame of(Position position) {
        return new BowlingFinalFrame(position, Score.of(Point.of(0), Point.of(0)));
    }

    @Override
    public Position position() {
        return position;
    }

    @Override
    public BowlingFrame second(Point point) {
        return new BowlingNormalFrame(position, score.next(point));
    }

    public boolean isOneMoreTime() {
        return score.score() != BowlingRole.MISS;
    }
}
