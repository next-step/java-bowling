package bowling.domain;

public class BowlingRound {
    public static final int LAST_ROUND_NUM = 10;
    private final int roundNumber;
    private final Scores scores = new Scores();

    public BowlingRound(int roundNumber) {
        if (roundNumber <= 0 || roundNumber > LAST_ROUND_NUM) {
            throw new IllegalArgumentException("볼링 라운드는 음수 또는 최대 라운드 수를 초과할수 없습니다. ");
        }
        this.roundNumber = roundNumber;
    }

    public BowlingRound next() {
        return new BowlingRound(roundNumber + 1);
    }

    public boolean isSameRound(BowlingRound target) {
        return this.roundNumber == target.roundNumber;
    }

    public void addKnockDownPins(int pins) {
        scores.add(pins);
    }

    public boolean isNextRound() {
        return !isLastRound() && scores.isNormalRoundEnd();
    }

    private boolean isLastRound() {
        return roundNumber == LAST_ROUND_NUM;
    }

    public boolean isFinish() {
        return isLastRound() && scores.isLastRoundEnd();
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public Scores getScores() {
        return scores;
    }

    public int sumScores() {
        return this.scores.sum();
    }

    public boolean containsStrike() {
        return scores.containsStrike();
    }

    public boolean containsSpare() {
        return scores.containsSpare();
    }

    public boolean isSelfCalculable() {
        return (this.isLastRound() && scores.isLastRoundEnd()) ||
                (!scores.containsSpare() && !scores.containsStrike() && scores.isNormalRoundEnd());
    }

    public boolean hasScore() {
        return this.scores.hasScore();
    }

    public boolean hasTwoScore() {
        return this.scores.hasTwoScore();
    }

    public boolean isFirstScoreStrike() {
        return scores.isFirstScoreStrike();
    }

    public Integer spareBonus() {
        return scores.spareBonus();
    }

    public Integer doubleStrikeBonus() {
        return scores.doubleStrikeBonus();
    }
}
