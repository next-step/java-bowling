package bowling.domain;

public class BowlingNormalFrame implements BowlingFrame {

    private Position position;
    private Score score;

    public BowlingNormalFrame(Position position, Score score) {
        this.position = position;
        this.score = score;
    }

    public static BowlingNormalFrame first() {
        return new BowlingNormalFrame(Position.of(0), Score.of());
    }

    @Override
    public BowlingFrame play() {
        if (position.isFinal()) {
            return BowlingFinalFrame.of(position.next(), Score.of());
        }
        return new BowlingNormalFrame(position.next(), Score.of());
    }

    public Position position() {
        return this.position;
    }
}
