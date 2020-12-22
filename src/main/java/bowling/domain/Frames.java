package bowling.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Frames {

    private Map<Integer, BowlingKnockDown> bowlingKnockDownMap = new LinkedHashMap<>();

    public Frames() {

    }

    public Map<Integer, BowlingKnockDown> getBowlingKnockDownMap() {
        return bowlingKnockDownMap;
    }

    public int size() {
        return this.bowlingKnockDownMap.size();
    }

    public void put(int number, BowlingKnockDown bowlingKnockDown) {
        this.bowlingKnockDownMap.put(number, bowlingKnockDown);
    }
}
