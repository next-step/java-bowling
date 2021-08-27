package bowling.step2.outputView.shape;

import bowling.step2.domain.Frame;
import bowling.step2.outputView.shape.shapeDetail.ShapeOfFirst;

public class Strike implements FrameResult {
    private final Frame frame;

    public Strike(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void print() {
        System.out.print("|    "
                + ShapeOfFirst.of(frame.countOfFirst())
                + "    ");
    }
}
