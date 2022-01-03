package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Round {

    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 10;
    private static final int LAST_NORMAL_ROUND = 9;

    private static final Map<Integer, Round> CACHE_ROUND;

    private final int round;

    static {
        CACHE_ROUND = new HashMap<>();
        IntStream.rangeClosed(MINIMUM, MAXIMUM)
            .forEach(num -> CACHE_ROUND.put(num, new Round(num)));
    }

    private Round(int round) {
        valid(round);
        this.round = round;
    }

    public static Round first() {
        return CACHE_ROUND.get(MINIMUM);
    }

    public static Round last() {
        return CACHE_ROUND.get(MAXIMUM);
    }

    public Round next() {
        int nextRound = round + 1;
        if (nextRound > MAXIMUM) {
            throw new IllegalArgumentException("존재하지 않는 라운드 입니다.");
        }

        return CACHE_ROUND.get(nextRound);
    }

    public boolean isNextFinishRound() {
        return round == LAST_NORMAL_ROUND;
    }

    public int round() {
        return round;
    }

    private void valid(int round) {
        if (round < MINIMUM || round > MAXIMUM) {
            throw new IllegalArgumentException("%d ~ %d 라운드만 이용 가능합니다.");
        }
    }

}
