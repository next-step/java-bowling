package bowling.domain.frames;

import bowling.domain.Frame;
import bowling.domain.FrameEnum;

class FramesMediator {
    private FramesMediator() {}

    static void notify(FramesContext context, Frame frame) {
        FramesState nextState = frame.getFrameEnum() == FrameEnum.UNFINISHED
                ? UnfinishedFramesState.getInstance()
                : FinishedFramesState.getInstance();
        context.setState(nextState);
    }

    static void notify(FramesContext context) {
        context.setState(FinishedFramesState.getInstance());
    }
}
