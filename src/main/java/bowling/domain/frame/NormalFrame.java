package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Point;

import java.util.Collections;
import java.util.List;

public class NormalFrame extends Frame {

    private int addedBonusCount = 0;

    public NormalFrame(Point before) {
        super(before);
    }

    public static NormalFrame start() {
        return new NormalFrame(Point.start());
    }

    public static NormalFrame of(Frame frame) {
        return new NormalFrame(frame.point);
    }

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public void bowl(Pin pin) {
        this.state = state.bowl(pin);
        this.point.add(pin);
    }

    @Override
    public void addPoint(Pin pin) {
        this.point.add(pin);
        this.addedBonusCount++;
    }

    @Override
    public boolean canAddPoint() {
        return addedBonusCount < state.bonusCount();
    }

    @Override
    public List<Pin> getBonus() {
        return Collections.emptyList();
    }
}
