package bowling.domain.frame;

import bowling.domain.score.FinalScores;

public class FinalFrame extends Frame {
    protected FinalScores finalScores;

    public FinalFrame() {
        this(10);
    }

    public FinalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.finalScores = new FinalScores();
    }

    public FinalFrame next(int number) {
        this.finalScores.add(number);
        if (this.finalScores.done()) {
            return new FinalFrame(this.frameNo + 1);
        }
        return this;
    }

    public FinalScores getFinalScores() {
        return this.finalScores;
    }
}
