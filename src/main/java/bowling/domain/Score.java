package bowling.domain;

public class Score {
    private int count;

    public Score(int count) {
        this.count = count;
    }

    public Score hit(int count) {
        return new Score(this.count + count);
    }

    public int toInt() {
        return count;
    }
}
