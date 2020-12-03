package bowling.domain.frames;

import bowling.domain.Frame;
import bowling.domain.FrameEnum;

import static bowling.asset.Const.MAX_FRAME_NO;

class FramesMediator {
    private FramesMediator() {}

    static void notify(FramesContext context, Frame frame) {
        FramesState nextState = frame.getFrameEnum() == FrameEnum.UNFINISHED
                ? UnfinishedFramesState.getInstance()
                : getNextState(context);
        context.setState(nextState);
    }

    static void notify(FramesContext context) {
        FramesState nextState = getNextState(context);
        context.setState(nextState);
    }

    private static FramesState getNextState(FramesContext context) {
        return context.size() < MAX_FRAME_NO
                ? FinishedFramesState.getInstance()
                : GameOverFramesState.getInstance();
    }
}
