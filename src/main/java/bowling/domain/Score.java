package bowling.domain;

public class Score {
    private int first;
    private int second;


    private Score() {
    }

    public Type first(int point) {
        if (point > 10 || point < 0) {
            throw new IllegalArgumentException("");
        }
        this.first = point;
        if (point == 10) {
            return Type.STRIKE;
        }
        return Type.NONE;
    }


}
