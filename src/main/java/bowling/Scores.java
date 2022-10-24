package bowling;

import java.util.ArrayList;
import java.util.List;

public class Scores {

    private static final int NORMAL_ROUND_TRY_NUM = 2;

    private static final int MAX_SCORE = 10;

    private final List<Integer> scores =  new ArrayList<>();

    public void add(Integer pins) {
        scores.add(pins);
    }

    public boolean isFull() {
        return scores.size() == NORMAL_ROUND_TRY_NUM || getSum() == MAX_SCORE;
    }

    private int getSum(){
        return scores.stream()
                .reduce(Integer::sum)
                .orElseThrow(AssertionError::new);
    }
}
