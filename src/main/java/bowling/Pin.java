package bowling;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Pin {

    private final static Map<Integer, Pin> cache = new HashMap<>();
    private final static int MIN_PIN_COUNT = 0;
    private final static int MAX_PIN_COUNT = 10;

    private final int hitCount;

    static {
        IntStream.rangeClosed(MIN_PIN_COUNT, MAX_PIN_COUNT)
            .forEach(number -> cache.put(number, new Pin(number)));
    }

    private Pin(int hitCount) {
        valid(hitCount);

        this.hitCount = hitCount;
    }

    public static Pin of(int score) {
        return cache.getOrDefault(score, new Pin(score));
    }

    public boolean isStrike() {
        return hitCount == MAX_PIN_COUNT;
    }

    public boolean isSpare(Pin beforeHitPin) {
        return (this.hitCount + beforeHitPin.hitCount) == MAX_PIN_COUNT;
    }

    public boolean isGutter() {
        return hitCount == MIN_PIN_COUNT;
    }

    public int getHitCount() {
        return hitCount;
    }

    private void valid(int score) {
        if (score < MIN_PIN_COUNT) {
            throw new IllegalArgumentException("Pin 갯수는 음수가 나올 수 없어요.");
        }

        if (score > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("Pin 갯수는 10개를 넘길 수 없어요.");
        }
    }


    public Pin sum(Pin hitPin) {
        int sumPinCount = this.hitCount + hitPin.hitCount;
        return cache.getOrDefault(sumPinCount, new Pin(sumPinCount));
    }
}
