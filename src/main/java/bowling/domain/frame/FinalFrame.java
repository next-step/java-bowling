package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {

    private final int index;
    private final List<Integer> scores = new ArrayList<>();

    private int tryNo;
    private int remain;

    public FinalFrame(int index) {
        this(index, 3, 0);
    }

    public FinalFrame(int index, int tryNo, int remain) {
        this.index = index;
        this.tryNo = tryNo;
        this.remain = remain;
    }

    @Override
    public int score() {
        return 0;
    }

    @Override
    public int validateMoveToNextIndex() {
        return 0;
    }

    @Override
    public boolean equal(int index) {
        return this.index == index;
    }
}
