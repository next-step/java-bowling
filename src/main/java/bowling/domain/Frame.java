package bowling.domain;

import lombok.Getter;

public class Frame {
    public static final int MAX_FRAME_NUMBER = 10;
    public static final int MAX_FRAME_INDEX = MAX_FRAME_NUMBER - 1;
    private static final int ONE = 1;
    private static final int ZERO = 0;

    @Getter
    private int frameIndex;
    @Getter
    private FrameRounds frameRounds;

    private Frame(int frameIndex) {
        this.frameIndex = frameIndex;
        this.frameRounds = new FrameRounds();
    }

    public void roundPlay(int clearCount) {
        frameRounds.play(clearCount, isLastFrame());
    }

    public static Frame fistFrame() {
        return new Frame(ZERO);
    }

    public Frame next() {
        return new Frame(this.frameIndex + ONE);
    }

    public void updateBonus(int bonusScore) {
        frameRounds.updateBonus(bonusScore);
    }

    public boolean endCalculate() {
        return frameRounds.endCalculate();
    }

    public int getTotalScore() {
        return frameRounds.getTotalScore();
    }

    public void addScore(int score) {
        frameRounds.addScore(score);
    }

    public boolean isEndFrame() {
        return frameRounds.isEnd(isLastFrame());
    }

    public boolean availableBonus() {
        return frameRounds.availableBonus();
    }

    private boolean isLastFrame() {
        return frameIndex == MAX_FRAME_INDEX;
    }
}
