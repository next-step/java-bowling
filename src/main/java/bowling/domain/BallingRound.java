package bowling.domain;

public class BallingRound {
    public static final int LAST_ROUND_NUM = 10;
    private final int roundNumber;

    private Scores scores = new Scores();

    public BallingRound(int roundNumber) {
        if (roundNumber < 0){
            throw new IllegalArgumentException();
        }
        this.roundNumber = roundNumber;
    }

    public BallingRound next(){
        return new BallingRound(roundNumber+1);
    }

    public boolean isSameRound(BallingRound target){
        return this.roundNumber == target.roundNumber;
    }

    public boolean addKnockDownPins(int pins) {
        scores.add(pins);
        if (!isLastRound() && scores.isNormalRoundEnd()){
            return true;
        }
        return false;
    }

    private boolean isLastRound(){
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
