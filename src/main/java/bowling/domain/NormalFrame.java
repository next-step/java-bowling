package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame{

    private final int number;

    private final List<Integer> pintCounts = new ArrayList<>();

    public NormalFrame(int number) {
        this.number = number;
    }

    public int number() {
        return number;
    }
}
