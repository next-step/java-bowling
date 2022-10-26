package bowling.domain;

public class BallingRound {
    public static final int LAST_ROUND_NUM = 10;
    private final int roundNumber;

    private final Scores scores = new Scores();

    public BallingRound(int roundNumber) {
        if (roundNumber <= 0) {
            throw new IllegalArgumentException();
        }
        this.roundNumber = roundNumber;
    }

    public BallingRound next() {
        return new BallingRound(roundNumber + 1);
    }

    public boolean isSameRound(BallingRound target) {
        return this.roundNumber == target.roundNumber;
    }

    public boolean addKnockDownPins(int pins) {
        scores.add(pins);
        return isNextRound();
    }

    private boolean isNextRound() {
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
}
