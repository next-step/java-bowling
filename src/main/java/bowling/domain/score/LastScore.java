package bowling.domain.score;

/**
 * Created : 2020-12-22 오후 4:32
 * Developer : Seo
 */
public class LastScore extends Score {
    private final int sum;

    public LastScore(int sum) {
        this.sum = sum;
    }

    @Override
    public int getFrameScore() {
        return this.sum;
    }
}
