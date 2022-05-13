package bowling.domain.frame;

import java.util.HashMap;
import java.util.Map;

public class NormalFrameNo {

    public static final int MIN_NORMAL_FRAME_NO = 1;
    private static final int MAX_NORMAL_FRAME_NO = 9;
    private static final Map<Integer, NormalFrameNo> cache = new HashMap<>();

    static {
        for (int no = MIN_NORMAL_FRAME_NO; no <= MAX_NORMAL_FRAME_NO; no++) {
            cache.put(no, new NormalFrameNo(no));
        }
    }

    private final int no;

    private NormalFrameNo(int no) {
        this.no = no;
    }

    public static NormalFrameNo of(int no) {
        if (no < MIN_NORMAL_FRAME_NO || MAX_NORMAL_FRAME_NO < no) {
            throw new IllegalArgumentException("invalid frame number: " + no);
        }
        return cache.get(no);
    }

    public int next() {
        if (isMax()) {
            throw new IllegalStateException();
        }
        return no + 1;
    }

    public boolean isMax() {
        return no == MAX_NORMAL_FRAME_NO;
    }
}
