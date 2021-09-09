package bowling.domain.frame;

import bowling.domain.score.NormalScores;

public class NormalFrame extends Frame {
    protected NormalScores normalScores;

    public NormalFrame() {
        this(1);
    }

    public NormalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.normalScores = new NormalScores();
    }

    public NormalFrame next(int number) {
        normalScores.add(number);
        if (normalScores.done()) {
            return new NormalFrame(this.frameNo + 1);
        }
        return this;
    }

    public NormalScores getNormalScores() {
        return this.normalScores;
    }
}
