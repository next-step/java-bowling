package bowling.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Frames {

    private final Map<Integer, BowlingKnockDown> bowlingKnockDownMap = new LinkedHashMap<>();

    public Frames() {

    }

    public BowlingKnockDown initFirst(int number, int countOfKnockDown) {
        BowlingKnockDown bowlingKnockDown = new BowlingKnockDown(countOfKnockDown);
        bowlingKnockDownMap.put(number, bowlingKnockDown);
        return bowlingKnockDown;
    }

    public BowlingKnockDown initNext(int number, BowlingKnockDown bowlingKnockDown, int nextCountOfBowlingKnockDown) {
        String currentKnockDownExpression = bowlingKnockDown.getKnockDownExpression().trim();
        int countOfBowlingKnockDown = bowlingKnockDown.getCountOfBowlingKnockDown();

        bowlingKnockDown =
                new BowlingKnockDown(currentKnockDownExpression, countOfBowlingKnockDown, nextCountOfBowlingKnockDown);
        bowlingKnockDownMap.put(number, bowlingKnockDown);
        return bowlingKnockDown;
    }

    public Map<Integer, BowlingKnockDown> getBowlingKnockDownMap() {
        return bowlingKnockDownMap;
    }

    public String bowlingKnockDownExpression(int key) {
        return this.bowlingKnockDownMap.get(key).getKnockDownExpression();
    }

    public int size() {
        return this.bowlingKnockDownMap.size();
    }
}
