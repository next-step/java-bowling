package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {

    private final int number;

    private final List<Integer> pintCounts = new ArrayList<>();

    public NormalFrame(int number) {
        this.number = number;
    }

    public static NormalFrame first() {
        return new NormalFrame(1);
    }

    public FinalFrame last() {
        return new FinalFrame(number + 1);
    }

    public NormalFrame next() {
        return new NormalFrame(number + 1);
    }

    public int number() {
        return number;
    }

    @Override
    public void addPintCount(int pinCount) {
        pintCounts.add(pinCount);
    }

    @Override
    public List<Integer> pinCounts() {
        return pintCounts;
    }
}
