package bowling.domain.frame;

import bowling.domain.point.Point;

public class FinalFrame implements Frame {

    @Override
    public Frame bowl(Point point) {
        return null;
    }

    @Override
    public boolean isLastFrame() {
        return true;
    }
}
