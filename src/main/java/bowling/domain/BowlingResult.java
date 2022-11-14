package bowling.domain;

public class BowlingResult {

    private final int count;
    private final Result result;

    public BowlingResult(int count, Result result) {
        this.count = count;
        this.result = result;
    }

    public static BowlingResult from(Frame frame, int count) {
        return new BowlingResult(count, Result.from(frame, count));
    }

    public Result getResult() {
        return result;
    }
}
