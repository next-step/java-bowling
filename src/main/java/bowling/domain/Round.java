package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Round {

    private static final Map<Integer, Round> cache = new HashMap<>();
    private static final int MIN = 0;
    private static final int MAX = 9;

    private final int index;

    static {
        IntStream.rangeClosed(MIN, MAX)
            .forEach(index -> cache.put(index, new Round(index)));
    }

    private Round(int index) {
        if (index < MIN) {
            throw new IllegalArgumentException("프레임은 최소 1라운드 부터 시작합니다.");
        }

        if (index > MAX) {
            throw new IllegalArgumentException("프레임은 최대 10라운드 까지밖에 존재하지 않아요.");
        }

        this.index = index;
    }

    public static Round of(int index) {
        return cache.getOrDefault(index, new Round(index));
    }

    public boolean isStartRound() {
        return this.index == MIN;
    }

    public Round before() {
        int beforeIndex = this.index - 1;
        return cache.getOrDefault(beforeIndex, new Round(beforeIndex));
    }

    public int getIndex() {
        return index;
    }
}
