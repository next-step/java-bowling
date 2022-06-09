package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        int score = new Random().nextInt(10 - remain) + 1;
        remain += score;
        tryNo--;
        if (tryNo < 2 && remain == 0) {
            int score2 = new Random().nextInt(10 - remain) + 1;
            remain += score;
            tryNo--;
        }

        return score;
    }

    @Override
    public int validateMoveToNextIndex() {
        if (tryNo < 1) { // 3번 던지면 무조건 끝
            return index + 1;
        }
        if (tryNo < 2 && remain > 0) { // 2번 던졌는데 스페어나 스트라이크가 아니면 끝
            return index + 1;
        }
        return index;
    }

    @Override
    public boolean equal(int index) {
        return this.index == index;
    }
}
