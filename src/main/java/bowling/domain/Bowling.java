package bowling.domain;

import javax.management.MXBean;

public class Bowling {

    public static final int MIN = 0;
    public static final int MAX = 10;
    private final int count;

    public Bowling(int count) {
        if (isNotValidCount(count)) {
            throw new IllegalArgumentException("0 ~ 10 사이의 정수만 가능합니다");
        }

        this.count = count;
    }

    private boolean isNotValidCount(int count) {
        return count < MIN || count > MAX;
    }

    public static Bowling of(int count) {
        return new Bowling(count);
    }

    public int getCount() {
        return count;
    }
}
