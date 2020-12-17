package bowling.domain;

/**
 * Created : 2020-12-16 오전 8:22
 * Developer : Seo
 */
public class Score {
    private final int first;
    private int second;
    private int tenFrameBonus;

    public Score(int downPins) {
        this.first = downPins;
        this.second = Frame.INIT;
    }

    public int get() {
        return first + second;
    }

    public int getFirst() {
        return first;
    }

    public void setSecond(int downPins) {
        this.second = downPins;
    }

    public void setTenFrameBonus(int downPins) {
        this.tenFrameBonus = downPins;
    }

    @Override
    public String toString() {
        return "Score{" +
                "first=" + first +
                ", second=" + second +
                ", tenFrameBonus=" + tenFrameBonus +
                '}';
    }
}
