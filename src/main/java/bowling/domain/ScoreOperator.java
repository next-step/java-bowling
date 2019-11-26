package bowling.domain;

public class ScoreOperator {

    private Integer first;
    private Integer second;

    private ScoreOperator(Integer first, Integer second) {
        this.first = first;
        this.second = second;
    }

    private static ScoreOperator of(Integer first, Integer second) {
        return new ScoreOperator(first, second);
    }

    public static ScoreOperator first(Integer first) {
        return of(first, null);
    }

    public ScoreOperator second(Integer second) {
        return of(this.first, second);
    }

    public Integer getFirst() {
        return first;
    }

    public Integer getSecond() {
        return second;
    }

    public boolean isSpike() {
        return this.first == 10;
    }

    public boolean isGutter() {
        return this.first == 0 && this.second == null;
    }

    public boolean isSpare() {
        if (this.second != null) {
            return this.first + this.second == 10;
        }
        return false;
    }
}
