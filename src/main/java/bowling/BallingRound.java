package bowling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BallingRound {

    public static final int LAST_ROUND_NUM = 10;
    private final int roundNumber;

    private Scores scores = new Scores();

    public BallingRound(int roundNumber) {
        if (roundNumber <= 0){
            throw new IllegalArgumentException();
        }
        this.roundNumber = roundNumber;
    }

    public boolean isSameRound(BallingRound target){
        return this.roundNumber == target.roundNumber;
    }

    public BallingRound addKnockDownPins(int pins) {
        scores.add(pins);
        if (scores.isFull() && !isLastRound()){
            return new BallingRound(roundNumber + 1);
        }
        return this;
    }

    private boolean isLastRound(){
        return roundNumber == LAST_ROUND_NUM;
    }


}
