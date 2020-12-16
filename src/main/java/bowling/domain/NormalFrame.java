package bowling.domain;

/**
 * Created : 2020-12-16 오전 8:16
 * Developer : Seo
 */
public class NormalFrame extends Frame {

    public NormalFrame(int frameNo, Score score) {
        super(frameNo, score);
        firstShot();
    }

    private Frame firstShot() {
        if (isStrike()) {
            return nextFrame(bowl());
        }
        secondShot(bowl());
        return nextFrame(bowl());
    }

    public boolean isMiss(Score second) {
        return score.get() != Symbol.STRIKE.getScore()
                && score.get() + second.get() != Symbol.SPARE.getScore()
                && score.get() + second.get() != Symbol.GUTTER.getScore();
    }

    public boolean isGutter(Score second) {
        return score.get() + second.get() == Symbol.GUTTER.getScore();
    }
}
