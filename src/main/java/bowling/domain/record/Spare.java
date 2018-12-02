package bowling.domain.record;

import static bowling.utils.BowlingConstants.MAX_HIT;

public class Spare implements Record {

    private static final String SPARE = "/";

    private static Spare spare = new Spare();

    private Spare() {
    }

    public static Spare getInstance() {
       return spare;
    }

    public static boolean isSpare(int pinCount) {
        if(pinCount > MAX_HIT) {
            throw new IllegalArgumentException("볼링핀의 합계는 1~10 사이의 값임");
        }
        return pinCount == MAX_HIT;
    }

    @Override
    public int hitPinCount() {
        return MAX_HIT;
    }

    @Override
    public String recordToString() {
        return SPARE;
    }

    @Override
    public Record nextRecord(int nextPinCount) {
        return Record.ofPinCount(nextPinCount);
    }
}
