package bowling.domain;

/**
 * Created : 2020-12-16 오전 8:22
 * Developer : Seo
 */
public class Score {
    private final int first;
    private int second;

    public Score(int score) {
        this.first = score;
        this.second = Frame.INIT;
    }

    public int get() {
        return first + second;
    }

    public void set(int score) {
        this.second = score;
    }
}
