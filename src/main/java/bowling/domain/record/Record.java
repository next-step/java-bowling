package bowling.domain.record;

public interface Record {

    static Record ofPinCount(int pinCount) {
        if(Strike.isStrike(pinCount)) {
            return Strike.getInstance();
        }

        if(Gutter.isGutter(pinCount)) {
            return Gutter.getInstance();
        }

        return Miss.getInstance(pinCount);
    }

    int hitPinCount();
    String recordToString();
    Record nextRecord(int nextPinCount);
}
