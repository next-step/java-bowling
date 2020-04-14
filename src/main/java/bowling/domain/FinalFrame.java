package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {
    private static final int TRYABLE_COUNT = 3;

    private List<Point> points;

    public FinalFrame() {
        points = new ArrayList<>();
    }

    @Override
    public boolean throwBall(int fallenCount) {
        return false;
    }

    @Override
    public boolean isThrowable() {
        return false;
    }
}
