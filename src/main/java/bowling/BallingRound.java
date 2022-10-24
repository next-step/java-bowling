package bowling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BallingRound {

    public static final int NORMAL_ROUND_TRY_NUM = 2;

    public static final int LAST_ROUND_TRY_NUM = 3;

    public static final int LAST_ROUND_NUM = 10;
    private final int roundNumber;

    private List<Integer> scores = new ArrayList<>();

    public BallingRound(int roundNumber) {
        if (roundNumber <= 0){
            throw new IllegalArgumentException();
        }
        this.roundNumber = roundNumber;
    }
    public BallingRound(int roundNumber , Integer  pins) {
        if (roundNumber <= 0) {
            throw new IllegalArgumentException();
        }
        this.scores.add(pins);
        this.roundNumber = roundNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BallingRound that = (BallingRound) o;
        return roundNumber == that.roundNumber && Objects.equals(scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundNumber, scores);
    }

    public boolean isSameRound(BallingRound target){
        return this.roundNumber == target.roundNumber;
    }

    public BallingRound add(int numberOfPins) {
        if (isEndOfRound()){
            return new BallingRound(roundNumber + 1 , numberOfPins);
        }
        scores.add(numberOfPins);
        return this;
    }

    private boolean isEndOfRound() {
        return roundNumber != LAST_ROUND_NUM &&
                scores.size() == NORMAL_ROUND_TRY_NUM;
    }

}
