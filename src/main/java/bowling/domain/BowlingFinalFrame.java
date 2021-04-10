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

    @Override
    public BowlingFrame play() {
        return BowlingFinalFrame.of(position.next(), Score.of());
    }

    @Override
    public Position position() {
        return position;
    }

    public boolean isOneMoreTime() {
        return score.score() != BowlingRole.MISS;
    }
}
