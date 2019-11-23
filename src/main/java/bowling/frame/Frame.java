package bowling.frame;

import bowling.score.BonusScore;
import bowling.score.rollling.Rolling;

import java.util.List;

public class Frame {
    private static final String BONUS_WITHOUT_FINAL_FRAME_EXCEPTION = "보너스 게임은 10번째 frame에서만 가능합니다";
    private final FrameType frameType;
    private FrameScore frameScore;
    private Frame prevFrame;
    private Integer scoreForFrame;

    private Frame(FrameType frameType) {
        this.frameScore = new FrameScore();
        this.frameType = frameType;
    }

    public static Frame of(int score) {
        Frame frame = new Frame(FrameType.NORMAL);
        frame.addScore(score);
        return frame;
    }

    public static Frame of(int score, Frame prevFrame) {
        Frame frame = new Frame(FrameType.NORMAL);
        frame.prevFrame = prevFrame;
        frame.addScore(score);
        return frame;
    }

    public static Frame finalOf(int score) {
        Frame frame = new Frame(FrameType.FINAL);
        frame.addScore(score);
        return frame;
    }

    public static Frame finalOf(int score, Frame prevFrame) {
        Frame frame = new Frame(FrameType.FINAL);
        frame.prevFrame = prevFrame;
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
        if (frameType == FrameType.NORMAL) {
            throw new IllegalArgumentException(BONUS_WITHOUT_FINAL_FRAME_EXCEPTION);
        }
        this.frameScore.addBonus(bonusScore);
        setScoreForFrame();
    }

    public BonusScore getBonus() {
        return this.frameScore.getBonus();
    }

    public FrameScoreType getGameType() {
        return this.frameScore.getGameType();
    }

    public List<Rolling> getScores() {
        return this.frameScore.getScores();
    }

    public Integer getScoreSum() {
        if (this.scoreForFrame == null) {
            return -1;
        }
        return scoreForFrame;
    }

    private int sumScore() {
        return frameScore.sumScore();
    }

    private void setScoreForFrame() {
        FrameScoreType frameScoreType = this.getGameType();
        setThisFrameScore(frameScoreType);

        if (this.prevFrame == null) {
            return;
        }

        setPreviousFrame(frameScoreType);
        if (this.prevFrame.prevFrame != null) {
            setPreviousOfPreviousFrameScore();
        }
    }

    private void setThisFrameScore(FrameScoreType frameScoreType) {
        if (frameScoreType == FrameScoreType.MISS) {
            this.scoreForFrame = frameScore.sumScore();
            return;
        }

        if (this.frameType != FrameType.FINAL || this.getBonus() == null) {
            return;
        }

        if (frameScoreType == FrameScoreType.SPARE) {
            this.scoreForFrame = frameScore.sumScore() + frameScore.sumBonus();
        }

        if (frameScoreType == FrameScoreType.STRIKE
                && this.getBonus().getNeedScoreCount() == 0) {
            this.scoreForFrame = this.sumScore() + this.frameScore.sumBonus();
        }
    }

    private void setPreviousFrame(FrameScoreType frameScoreType) {
        if ((frameScoreType == FrameScoreType.MISS || frameScoreType == FrameScoreType.SPARE)
                && this.frameType == FrameType.FINAL
                && this.prevFrame.getGameType() == FrameScoreType.STRIKE) {
            this.prevFrame.scoreForFrame = this.prevFrame.sumScore() + this.sumScore();
        }

        if ((frameScoreType == FrameScoreType.MISS || frameScoreType == FrameScoreType.SPARE)
                && this.prevFrame.getGameType() == FrameScoreType.STRIKE) {
            this.prevFrame.scoreForFrame = this.prevFrame.sumScore() + this.sumScore();
        }

        if (frameScoreType == FrameScoreType.STRIKE
                && this.frameType == FrameType.FINAL
                && this.getBonus() != null
                && this.prevFrame.getGameType() == FrameScoreType.STRIKE) {
            this.prevFrame.scoreForFrame = this.prevFrame.sumScore() + this.sumScore() + frameScore.sumBonus();
        }

        if (this.prevFrame.getGameType() == FrameScoreType.SPARE) {
            this.prevFrame.scoreForFrame = this.prevFrame.sumScore() + this.getScores().get(0).getScore();
        }
    }

    private void setPreviousOfPreviousFrameScore() {
        if (this.prevFrame.getGameType() == FrameScoreType.STRIKE
                && this.prevFrame.prevFrame.getGameType() == FrameScoreType.STRIKE) {
            this.prevFrame.prevFrame.scoreForFrame = this.prevFrame.prevFrame.sumScore()
                    + this.prevFrame.sumScore() + this.getScores().get(0).getScore();
        }
    }
}