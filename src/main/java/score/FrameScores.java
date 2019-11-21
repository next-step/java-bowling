package score;

import game.GameType;

import java.util.List;

public class FrameScores {
    private static final String ROLLING_COUNT_OVER_THREE = "한 프레임에 세번 이상 공을 던질 수 없습니다.";
    private BasicScores scores;
    private BonusScores bonus;

    public FrameScores() {
        this.scores = new BasicScores();
    }

    public void addScore(int score) {
        if (this.scores.size() > 1) {
            throw new IllegalArgumentException(ROLLING_COUNT_OVER_THREE);
        }
        this.scores.addScore(score);
    }

    public void addBonus(int bonusScore) {

        if (this.bonus == null) {
            this.bonus = new BonusScores();
        }
        this.bonus.addScore(getGameType(), bonusScore);
    }

    public int sumScore() {
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

    public int sumBonus() {
        return bonus.sumScore();
    }
}
