package bowling.domain;

import java.util.List;

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

    public boolean containsScore(List<Score> scores){
        return this.scores.containsAll(scores);
    }

    public boolean isSelfCalculable() {
        // TODO
        return false;
    }
}
