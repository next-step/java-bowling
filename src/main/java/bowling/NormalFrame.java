package bowling;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {

    private final static int MAX_ROUND = 2;

    private final int position;

    private final List<Integer> downPins = new ArrayList<>();

    private NormalFrame(int position) {
        this.position = position;
    }

    static NormalFrame first() {
        return new NormalFrame(0);
    }

    NormalFrame next() {
        return new NormalFrame(this.position + 1);
    }

    @Override
    public void play(int downPin) {
        if (!hasTurn()) {
            throw new IllegalStateException("이미 종료된 frame입니다.");
        }

        this.downPins.add(downPin);
    }

    @Override
    public boolean hasTurn() {
        if (this.downPins.size() == MAX_ROUND) {
            return false;
        }

        if (sumDownPin() == 10) {
            return false;
        }

        return true;
    }

    @Override
    public FrameResult getResult() {
        return new FrameResult(new ArrayList<>(this.downPins));
    }

    private int sumDownPin() {
        return downPins.stream().reduce(0, Integer::sum);
    }
}
