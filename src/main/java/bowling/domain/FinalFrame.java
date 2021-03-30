package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame{

    private final int number;

    private final List<Integer> pintCounts = new ArrayList<>();

    public FinalFrame(int number) {
        this.number = number;
    }


    @Override
    public int number() {
        return number;
    }
}
