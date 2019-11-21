package game;

import score.BonusScores;
import score.FrameScores;
import score.Score;

import java.util.List;

public class Frame {
    private static final String BONUS_WITHOUT_FINAL_FRAME = "보너스 게임은 10번째 frame에서만 가능합니다";
    private final FrameType frameType;
    private FrameScores frameScores;
    private Frame prevFrame;
    private Integer scoreForFrame;

    private Frame(FrameType frameType) {
        this.frameScores = new FrameScores();
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
        this.frameScores.addScore(score);
        setScoreForFrame();
    }

    public void addBonus(int bonusScore) {
        if (frameType == FrameType.NORMAL) {
            throw new IllegalArgumentException(BONUS_WITHOUT_FINAL_FRAME);
        }
        this.frameScores.addBonus(bonusScore);
        setScoreForFrame();
    }

    public BonusScores getBonus() {
        return this.frameScores.getBonus();
    }

    public GameType getGameType() {
        return this.frameScores.getGameType();
    }

    public List<Score> getScores() {
        return this.frameScores.getScores();
    }

    public Integer getScoreSum() {
        if (this.scoreForFrame == null) {
            return -1;
        }
        return scoreForFrame;
    }

    private int sumScore() {
        return frameScores.sumScore();
    }

    private void setScoreForFrame() {
        GameType gameType = this.getGameType();
        setThisFrameScore(gameType);

        if (this.prevFrame == null) {
            return;
        }

        setPreviousFrame(gameType);
        if (this.prevFrame.prevFrame != null) {
            setPreviousOfPreviousFrameScore();
        }
    }

    private void setThisFrameScore(GameType gameType) {
        if (gameType == GameType.MISS) {
            this.scoreForFrame = frameScores.sumScore();
            return;
        }

        if (this.frameType != FrameType.FINAL || this.getBonus() == null) {
            return;
        }

        if (gameType == GameType.SPARE) {
            this.scoreForFrame = frameScores.sumScore() + frameScores.sumBonus();
        }

        if (gameType == GameType.STRIKE
                && this.getBonus().getNeedScoreCount() == 0) {
            this.scoreForFrame = this.sumScore() + this.frameScores.sumBonus();
        }
    }

    private void setPreviousFrame(GameType gameType) {
        if ((gameType == GameType.MISS || gameType == GameType.SPARE)
                && this.frameType == FrameType.FINAL
                && this.prevFrame.getGameType() == GameType.STRIKE) {
            this.prevFrame.scoreForFrame = this.prevFrame.sumScore() + this.sumScore();
        }

        if ((gameType == GameType.MISS || gameType == GameType.SPARE)
                && this.prevFrame.getGameType() == GameType.STRIKE) {
            this.prevFrame.scoreForFrame = this.prevFrame.sumScore() + this.sumScore();
        }

        if (gameType == GameType.STRIKE
                && this.frameType == FrameType.FINAL
                && this.getBonus() != null
                && this.prevFrame.getGameType() == GameType.STRIKE) {
            this.prevFrame.scoreForFrame = this.prevFrame.sumScore() + this.sumScore() + frameScores.sumBonus();
        }

        if (this.prevFrame.getGameType() == GameType.SPARE) {
            this.prevFrame.scoreForFrame = this.prevFrame.sumScore() + this.getScores().get(0).getScore();
        }
    }

    private void setPreviousOfPreviousFrameScore() {
        if (this.prevFrame.getGameType() == GameType.STRIKE
                && this.prevFrame.prevFrame.getGameType() == GameType.STRIKE) {
            this.prevFrame.prevFrame.scoreForFrame = this.prevFrame.prevFrame.sumScore()
                    + this.prevFrame.sumScore() + this.getScores().get(0).getScore();
        }
    }
}