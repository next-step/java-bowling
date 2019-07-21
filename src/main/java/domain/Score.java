package domain;

public class Score {

    private int point;
    private int remainingAddition;

    private Score(int point, int remainingAddition) {
        this.point = point;
        this.remainingAddition = remainingAddition;
    }

    public static Score of(int point, int remainingAddition) {
        return new Score(point, remainingAddition);
    }
}
