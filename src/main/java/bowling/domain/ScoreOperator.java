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
}
