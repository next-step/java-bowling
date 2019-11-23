package bowling.frame;

import bowling.score.BonusScore;
import bowling.score.NormalScore;
import bowling.score.rollling.Rolling;

import java.util.List;

public class FrameScore {
    private static final String ROLLING_COUNT_EXCEPTION = "한 프레임에 세번 이상 공을 던질 수 없습니다.";
    private NormalScore scores;
    private BonusScore bonus;

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

    public BonusScore getBonus() {
        return this.bonus;
    }

    public FrameScoreType getGameType() {
        int sumScore = sumScore();
        return FrameScoreType.get(scores.size(), sumScore);
    }

    public List<Rolling> getScores() {
        return this.scores.getRollings();
    }

    public int sumBonus() {
        return bonus.sumScore();
    }
}
