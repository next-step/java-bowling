package bowling;

public class BallingRound {

    private final int roundNumber;

    private  Scores scores;

    public BallingRound(int roundNumber) {
        if (roundNumber <= 0){
            throw new IllegalArgumentException();
        }
        this.roundNumber = roundNumber;
    }
}
