package bowling.domain.vo;

public class BowlingBalls {
    public static final BowlingBalls INITIAL_BOWLING_BALLS = new BowlingBalls(10);
    private final int number;

    public BowlingBalls(final int number) {
        if (number < 0 || number > 10) {
            throw new IllegalArgumentException("볼링공 숫자는 0-10 사이어야합니다.");
        }
        this.number = number;
    }

    public BowlingBalls minus(final BowlingBalls bowlingBalls) {
        return new BowlingBalls(this.number - bowlingBalls.number);
    }

    public int getNumber() {
        return number;
    }
}
