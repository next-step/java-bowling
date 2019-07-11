package domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private int round;
    private List<Integer> fallenPins;

    private Frame(int round, int fallenPins) {
        this.round = round;
        this.fallenPins = new ArrayList<>(fallenPins);
    }

    public static Frame of(int round, int fallenPins) {
        return new Frame(round, fallenPins);
    }
}
