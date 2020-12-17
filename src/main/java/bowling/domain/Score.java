package bowling.domain;

/**
 * Created : 2020-12-16 오전 8:22
 * Developer : Seo
 */
public class Score {
    private final int first;
    private int second;

    public Score(int pins) {
        this.first = pins;
        this.second = Frame.INIT;
    }

    public int get() {
        return first + second;
    }

    public int getFirst() {
        return first;
    }

    public void setSecond(int pins) {
        this.second = pins;
    }

    @Override
    public String toString() {
        return "Score{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
