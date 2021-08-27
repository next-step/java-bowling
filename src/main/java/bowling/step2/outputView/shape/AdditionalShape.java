package bowling.step2.outputView.shape;

import bowling.step2.domain.Count;
import bowling.step2.domain.Frame;
import bowling.step2.domain.LastFrame;
import bowling.step2.outputView.shape.shapeDetail.ShapeOfFirst;
import bowling.step2.outputView.shape.shapeDetail.ShapeOfSecond;

public class AdditionalShape implements FrameResult {
    private final Frame frame;

    public AdditionalShape(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void print() {
        System.out.print("|  "
                + ShapeOfFirst.of(frame.countOfFirst())
                + ShapeOfSecond.of(frame.countOfFirst().sum(frame.countOfSecond())));

        if (((LastFrame) frame).countOfAdditional() == Count.NONE) {
            return;
        }

        System.out.print("|" + ShapeOfFirst.of(((LastFrame) frame).countOfAdditional()));
    }
}
