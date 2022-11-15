package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private int currentRound = 0;
    private final List<Frame2> values;

    private Frames(List<Frame2> values) {
        this.values = values;
    }

    public static Frames init() {
        List<Frame2> values = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            values.add(Frame2.createNormal());
        }

        values.add(Frame2.createFinal());
        return new Frames(values);
    }

    public void bowling(int count) {
        Frame2 frame = getFrame2();
        frame.bowling(count);
    }

    public boolean canBowling() {
        return currentRound != 10 && !values.get(currentRound).isEnd();
    }

    private Frame2 getFrame2() {
        Frame2 current = values.get(currentRound);

        if (!current.isEnd()) {
            return current;
        }

        currentRound++;
        return values.get(currentRound);
    }

    public Frame2 get(int index) {
        return values.get(index);
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int size() {
        return values.size();
    }
}
