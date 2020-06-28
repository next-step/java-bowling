package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.domain.point.Points;

public class FinalFrame implements Frame {

    private static final int MAX_BOWL_PITCH = 2;

    private Points points;

    @Override
    public Frame bowl(Point point) {
        return null;
    }

    @Override
    public boolean isLastFrame() {
        return true;
    }
}
