package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private int currentRound = 0;
    private final List<Frame> values;

    private Frames(List<Frame> values) {
        this.values = values;
    }

    public static Frames init() {
        List<Frame> values = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            values.add(Frame.createNormal());
        }

        values.add(Frame.createFinal());
        return new Frames(values);
    }

    public void bowling(int count) {
        Frame frame = getFrame2();
        frame.bowling(count);
    }

    public boolean canBowling() {
        return currentRound != 10 && !values.get(currentRound).isEnd();
    }

    private Frame getFrame2() {
        Frame current = values.get(currentRound);

        if (!current.isEnd()) {
            return current;
        }

        currentRound++;
        return values.get(currentRound);
    }

    public Frame get(int index) {
        return values.get(index);
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int size() {
        return values.size();
    }
}
