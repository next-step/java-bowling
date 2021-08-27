package bowling.step2.outputView.shape;

import bowling.step2.domain.Count;
import bowling.step2.domain.Frame;
import bowling.step2.domain.LastFrame;
import bowling.step2.domain.NormalFrame;

public class FrameResultFactory {
    public static FrameResult create(Frame frame) {
        if (frame instanceof NormalFrame) {
            return makeNormalFrameResult((NormalFrame) frame);
        }

        return makeLastFrameResult((LastFrame) frame);
    }

    public static FrameResult makeNormalFrameResult(NormalFrame normalFrame) {
        if (normalFrame.countOfFirst() == Count.TEN) {
            return new Strike(normalFrame);
        }

        if (normalFrame.countOfFirst().sum(normalFrame.countOfSecond()) == Count.TEN) {
            return new Spare(normalFrame);
        }

        return new Miss(normalFrame);
    }

    public static FrameResult makeLastFrameResult(LastFrame lastFrame) {
        if (lastFrame.countOfFirst().sum(lastFrame.countOfSecond()) != Count.TEN) {
            return new Miss(lastFrame);
        }

        return new AdditionalShape(lastFrame);
    }
}
