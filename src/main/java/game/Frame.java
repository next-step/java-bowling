package game;

import score.BasicScores;
import score.BonusScores;
import score.Score;

import java.util.List;
import java.util.Objects;

public class Frame {
    private static final String BONUS_WITHOUT_FINAL_FRAME = "보너스 게임은 10번째 frame에서만 가능합니다";
    private static final String ROLLING_COUNT_OVER_THREE = "한 프레임에 세번 이상 공을 던질 수 없습니다.";
    private final FrameType frameType;
    private BasicScores scores;
    private BonusScores bonus;
    private Frame prevFrame;
    private Integer scoreForFrame;

    private Frame(FrameType frameType) {
        this.scores = new BasicScores();
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
        if (this.scores.size() > 1) {
            throw new IllegalArgumentException(ROLLING_COUNT_OVER_THREE);
        }
        this.scores.addScore(score);
        setScoreForFrame();
    }

    public void addBonus(int bonusScore) {
        if (frameType == FrameType.NORMAL) {
            throw new IllegalArgumentException(BONUS_WITHOUT_FINAL_FRAME);
        }
        if (this.bonus == null) {
            this.bonus = new BonusScores();
        }
        this.bonus.addScore(getGameType(), bonusScore);
        setScoreForFrame();
    }

    private int sumScore() {
        return scores.sumScore();
    }

    public BonusScores getBonus() {
        return this.bonus;
    }

    public GameType getGameType() {
        int sumScore = sumScore();
        return GameType.get(scores.size(), sumScore);
    }

    public List<Score> getScores() {
        return this.scores.getScores();
    }

    public int getScoreSum() {
        if (this.scoreForFrame == null) {
            return -1;
        } else {
            return this.scoreForFrame;
        }
    }

    private void setScoreForFrame() {
        GameType gameType = this.getGameType();
        if (gameType == GameType.MISS) {
            this.scoreForFrame = sumScore();
        }

        if (gameType == GameType.SPARE
                && this.frameType == FrameType.FINAL
                && this.bonus != null) {
            this.scoreForFrame = this.sumScore() + this.bonus.sumScore();
        }

        if (gameType == GameType.STRIKE
                && this.frameType == FrameType.FINAL
                && this.bonus != null && this.bonus.getNeedScoreCount() == 0) {
            this.scoreForFrame = this.sumScore() + this.bonus.sumScore();
        }

        if ((gameType == GameType.MISS || gameType == GameType.SPARE)
                && this.frameType == FrameType.FINAL
                && this.prevFrame != null && this.prevFrame.getGameType() == GameType.STRIKE) {
            this.prevFrame.scoreForFrame = this.prevFrame.sumScore() + this.sumScore();
        }

        if ((gameType == GameType.MISS || gameType == GameType.SPARE)
                && this.prevFrame != null && this.prevFrame.getGameType() == GameType.STRIKE) {
            this.prevFrame.scoreForFrame = this.prevFrame.sumScore() + this.sumScore();
        }

        if (gameType == GameType.STRIKE
                && this.frameType == FrameType.FINAL
                && this.bonus != null
                && this.prevFrame != null && this.prevFrame.getGameType() == GameType.STRIKE) {
            this.prevFrame.scoreForFrame = this.prevFrame.sumScore() + this.sumScore() + this.bonus.sumScore();
        }

        if (this.prevFrame != null && this.prevFrame.getGameType() == GameType.SPARE) {
            this.prevFrame.scoreForFrame = this.prevFrame.sumScore() + this.getScores().get(0).getScore();
        }

        if (this.prevFrame != null && this.prevFrame.getGameType() == GameType.STRIKE
                && this.prevFrame.prevFrame != null && this.prevFrame.prevFrame.getGameType() == GameType.STRIKE) {
            this.prevFrame.prevFrame.scoreForFrame = this.prevFrame.prevFrame.sumScore()
                    + this.prevFrame.sumScore() + this.getScores().get(0).getScore();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return frameType == frame.frameType && Objects.equals(scores, frame.scores) && Objects.equals(bonus, frame.bonus) && Objects.equals(prevFrame, frame.prevFrame) && Objects.equals(scoreForFrame, frame.scoreForFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameType, scores, bonus, prevFrame, scoreForFrame);
    }
}