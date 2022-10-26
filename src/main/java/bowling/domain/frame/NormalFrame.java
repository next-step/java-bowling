package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Point;

import java.util.Optional;

public class NormalFrame extends Frame {


    public NormalFrame() {
        super();
    }

    public NormalFrame(Frame before) {
        super(before);
    }

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public void bowl(Pin pin) {
        this.state = state.bowl(pin);
        addPoint();
    }

    private void addPoint() {
        if (isFinish()) {
            this.point = new Point(Optional.ofNullable(before)
                    .map(frame -> frame.point)
                    .orElse(null), state.getSum(), state.bonusCount());
        }
    }

    @Override
    public Integer calculatePoint() {
        if(point == null){
            return null;
        }
        return point.point();
    }

    @Override
    public void addPoint(Pin pin) {
        this.point.add(pin);
    }

    @Override
    public boolean canAddPoint() {
        return point.canAddPoint();
    }
}
