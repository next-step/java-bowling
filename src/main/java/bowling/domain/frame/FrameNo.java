package bowling.domain.frame;

import java.util.HashMap;
import java.util.Map;

public class FrameNo {

    private static final int MIN_NO = 1;
    private static final int MAX_NO = 10;

    private static final Map<Integer, FrameNo> cache = new HashMap<>();

    static {
        for (int no = MIN_NO; no <= MAX_NO; no++) {
            cache.put(no, new FrameNo(no));
        }
    }

    private final int no;

    private FrameNo(int no) {
        this.no = no;
    }

    public static FrameNo of(int no) {
        if (no < MIN_NO || no > MAX_NO) {
            throw new IllegalArgumentException();
        }
        return cache.get(no);
    }

    public int next() {
        if (isMax()) {
            throw new IllegalStateException("no more next frame");
        }
        return no + 1;
    }

    public boolean isMax() {
        return no == MAX_NO;
    }

    public int no() {
        return no;
    }
}
