package bowling.step2.outputView.shape;

import bowling.step2.domain.Frame;
import bowling.step2.outputView.shape.shapeDetail.ShapeOfFirst;
import bowling.step2.outputView.shape.shapeDetail.ShapeOfSecond;

public class Spare implements FrameResult {
    private final Frame frame;

    public Spare(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void print() {
        System.out.print("|   " + ShapeOfFirst.of(frame.countOfFirst())
                + ShapeOfSecond.of(frame.countOfFirst()
                .sum(frame.countOfSecond()))
                + "   ");
    }
}
