package bowling.domain.frame;

import bowling.domain.point.Point;

public class NormalFrame implements Frame {

    @Override
    public Frame bowl(Point point) {
        return null;
    }

    @Override
    public boolean isLastFrame() {
        return false;
    }
}
