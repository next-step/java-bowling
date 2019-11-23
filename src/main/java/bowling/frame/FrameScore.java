package bowling.frame;

import bowling.score.BonusScore;
import bowling.score.NormalScore;
import bowling.score.rollling.Rolling;

import java.util.List;

public class FrameScore {
    private static final String ROLLING_COUNT_EXCEPTION = "한 프레임에 세번 이상 공을 던질 수 없습니다.";
    private NormalScore scores;
    private BonusScore bonus;
    private Integer scoreForFrame = null;

    public FrameScore() {
        this.scores = new NormalScore();
    }

    public void addScore(int score) {
        if (this.scores.size() > 1) {
            throw new IllegalArgumentException(ROLLING_COUNT_EXCEPTION);
        }
        this.scores.addScore(score);
    }

    public void addBonus(int bonusScore) {
        if (this.bonus == null) {
            this.bonus = new BonusScore();
        }
        this.bonus.addScore(getGameType(), bonusScore);
    }

    public int sumScore() {
        return scores.sumScore();
    }

    private int sumBonus() {
        return bonus.sumScore();
    }

    public List<Rolling> getScores() {
        return this.scores.getRollings();
    }

    public BonusScore getBonus() {
        return this.bonus;
    }

    public FrameScoreType getGameType() {
        int sumScore = sumScore();
        return FrameScoreType.get(scores.size(), sumScore);
    }

    public Integer getScoreSum() {
        if (this.scoreForFrame == null) {
            return -1;
        }
        return scoreForFrame;
    }

    public void setThisFrameScore(FrameScoreType frameScoreType) {
        if (this.scoreForFrame != null) {
            return;
        }
        if (frameScoreType == FrameScoreType.MISS) {
            this.scoreForFrame = sumScore();
        }

        if (frameScoreType == FrameScoreType.SPARE
                && this.getBonus() != null) {
            this.scoreForFrame = sumScore() + sumBonus();
        }

        if (frameScoreType == FrameScoreType.STRIKE
                && this.getBonus() != null
                && this.getBonus().getNeedScoreCount() == 0) {
            this.scoreForFrame = sumScore() + sumBonus();
        }
    }

    public void setPreviousFrameWithFinal(FrameScoreType nextFrameScoreType, Frame next) {
        if (this.scoreForFrame != null) {
            return;
        }
        if (nextFrameScoreType == FrameScoreType.MISS || nextFrameScoreType == FrameScoreType.SPARE) {
            this.scoreForFrame = (sumScore() + next.sumScore());
        }

        if (nextFrameScoreType == FrameScoreType.STRIKE && next.getBonus() != null) {
            this.scoreForFrame = (sumScore() + next.sumScore() + next.getBonus().sumScore());
        }
    }

    public void setPreviousFrame(FrameScoreType nextFrameScoreType,
                                 FrameScoreType nowFrameScoreType,
                                 Frame next) {
        if (this.scoreForFrame != null) {
            return;
        }
        if ((nextFrameScoreType == FrameScoreType.MISS || nextFrameScoreType == FrameScoreType.SPARE)
                && nowFrameScoreType == FrameScoreType.STRIKE) {
            this.scoreForFrame = (this.sumScore() + next.sumScore());
        }
        if (nowFrameScoreType == FrameScoreType.SPARE && next.getScores().size() == 1) {
            this.scoreForFrame = (this.sumScore() + next.getScore(0));
        }
    }

    public void setPreviousOfPreviousFrameScore(FrameScoreType nextFrameScoreType,
                                                FrameScoreType nowFrameScoreType,
                                                Frame next2,
                                                Frame next) {
        if (this.scoreForFrame != null) {
            return;
        }
        if (nextFrameScoreType == FrameScoreType.STRIKE
                && nowFrameScoreType == FrameScoreType.STRIKE) {
            this.scoreForFrame = (this.sumScore() + next.sumScore() + next2.sumScore());
        }
    }
}
