package bowling.domain;

public class BowlingNormalFrame implements BowlingFrame {

    private final Position position;
    private final Score score;

    public BowlingNormalFrame(Position position, Score score) {
        this.position = position;
        this.score = score;
    }

    public static BowlingNormalFrame first(Point point) {
        return new BowlingNormalFrame(Position.of(0), Score.first(point));
    }

    public static BowlingNormalFrame of(int position, Point point) {
        return new BowlingNormalFrame(Position.of(position), Score.first(point));
    }

    public static BowlingFrame of(Position position) {
        return new BowlingNormalFrame(position, Score.of(Point.of(0), Point.of(0)));
    }

    public Position position() {
        return this.position;
    }

    @Override
    public BowlingFrame second(Point point) {
        return new BowlingNormalFrame(position, score.next(point));
    }

    public BowlingFrame nextFrame() {
        if (position.isFinal()) {
            return BowlingFinalFrame.of(position.next());
        }
        return BowlingNormalFrame.of(position.next());
    }


}
