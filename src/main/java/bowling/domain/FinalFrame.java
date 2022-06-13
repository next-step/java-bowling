package bowling.domain;

import bowling.engine.Frame;

public class FinalFrame implements Frame {
    public static final int FINAL_SIZE = 2;

    private NormalFrame normalFrame;
    private Pitching bonusPitching;

    private FinalFrame(NormalFrame normalFrame) {
        this.normalFrame = normalFrame;
    }

    private FinalFrame(NormalFrame normalFrame, int bonusNumber) {
        this.normalFrame = normalFrame;
        this.bonusPitching = new Pitching(bonusNumber);
    }

    public static FinalFrame first(int firstPitchingNumber) {
        return new FinalFrame(NormalFrame.first(firstPitchingNumber));
    }

    public FinalFrame second(int secondPitchingNumber) {
        return new FinalFrame(this.normalFrame.second(secondPitchingNumber));
    }

    public FinalFrame bonus(int bonusPitchingNumber) {
        return new FinalFrame(this.normalFrame, bonusPitchingNumber);
    }

    public FinalFrame of(NormalFrame normalFrame, int bonusNumber) {
        return new FinalFrame(normalFrame, bonusNumber);
    }

    @Override
    public boolean isEnd() {
        return this.normalFrame.isEnd();
    }

    public NormalFrame getNormalFrame() {
        return normalFrame;
    }

    public Pitching getBonusPitching() {
        return bonusPitching;
    }
}
