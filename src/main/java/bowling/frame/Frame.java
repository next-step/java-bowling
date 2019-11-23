package bowling.frame;

import bowling.score.BonusScore;
import bowling.score.rollling.Rolling;

import java.util.List;

public class Frame {
    private static final String BONUS_WITHOUT_FINAL_FRAME_EXCEPTION = "보너스 게임은 10번째 frame에서만 가능합니다";
    private final FrameType frameType;
    private Frame prevFrame;
    private FrameScore frameScore;

    private Frame() {
        this.frameScore = new FrameScore();
        this.frameType = FrameType.INITIAL;
    }

    private Frame(FrameType frameType, Frame prevFrame) {
        this.frameScore = new FrameScore();
        this.frameType = frameType;
        this.prevFrame = prevFrame;
    }

    public static Frame initialOf(int score) {
        Frame frame = new Frame();
        frame.addScore(score);
        return frame;
    }

    public static Frame of(int score, Frame prevFrame) {
        Frame frame = new Frame(FrameType.NORMAL, prevFrame);
        frame.addScore(score);
        return frame;
    }

    public static Frame finalOf(int score, Frame prevFrame) {
        Frame frame = new Frame(FrameType.FINAL, prevFrame);
        frame.addScore(score);
        return frame;
    }

    public FrameType getFrameType() {
        return frameType;
    }

    public Frame getPrevFrame() {
        return prevFrame;
    }

    public void addScore(int score) {
        this.frameScore.addScore(score);
        setScoreForFrame();
    }

    public void addBonus(int bonusScore) {
        if (frameType != FrameType.FINAL) {
            throw new IllegalArgumentException(BONUS_WITHOUT_FINAL_FRAME_EXCEPTION);
        }
        this.frameScore.addBonus(bonusScore);
        setScoreForFrame();
    }

    public BonusScore getBonus() {
        return this.frameScore.getBonus();
    }

    public FrameScoreType getFrameScoreType() {
        return this.frameScore.getGameType();
    }

    public List<Rolling> getScores() {
        return this.frameScore.getScores();
    }

    public Integer getScore(int index) {
        return this.frameScore.getScores().get(index).getScore();
    }

    public Integer getScoreSum() {
        return this.frameScore.getScoreSum();
    }

    public int sumScore() {
        return frameScore.sumScore();
    }

    private void setScoreForFrame() {
        FrameScoreType frameScoreType = this.getFrameScoreType();
        this.frameScore.setThisFrameScore(frameScoreType);

        if (frameType == FrameType.NORMAL) {
            setScoreForFrameWithNormalFrame(frameScoreType);
        }

        if (frameType == FrameType.FINAL) {
            setScoreForFrameWithFinalFrame(frameScoreType);
        }
    }

    private void setScoreForFrameWithNormalFrame(FrameScoreType frameScoreType) {
        Frame before = this.prevFrame;
        before.frameScore.setPreviousFrame(frameScoreType, before.getFrameScoreType(), this);

        Frame before2 = this.prevFrame.prevFrame;
        if (before2 != null) {
            before2.frameScore.setPreviousOfPreviousFrameScore
                    (before.getFrameScoreType(), before2.getFrameScoreType(), this, before);
        }
    }

    private void setScoreForFrameWithFinalFrame(FrameScoreType frameScoreType) {
        Frame before = this.prevFrame;
        if (before.getFrameScoreType() == FrameScoreType.STRIKE) {
            before.frameScore.setPreviousFrameWithFinal(frameScoreType, this);
        }

        Frame before2 = this.prevFrame.prevFrame;
        before2.frameScore.setPreviousOfPreviousFrameScore
                (before.getFrameScoreType(), before2.getFrameScoreType(), this, before);
    }
}