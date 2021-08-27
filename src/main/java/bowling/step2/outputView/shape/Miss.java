package bowling.step2.outputView.shape;

import bowling.step2.domain.Frame;
import bowling.step2.outputView.shape.shapeDetail.ShapeOfFirst;
import bowling.step2.outputView.shape.shapeDetail.ShapeOfSecond;

public class Miss implements FrameResult {
    private final Frame frame;

    public Miss(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void print() {
        System.out.print("|   " + ShapeOfFirst.of(frame.countOfFirst())
                + ShapeOfSecond.of(frame.countOfSecond())
                + "   ");
    }
}
