package bowling.domain.frames;

import bowling.domain.Frame;
import bowling.domain.FrameEnum;

import static bowling.asset.Const.MAX_FRAME_NO;

/**
 * NOTE: Mediator 에는 Context 의 State 를 바꾸는 로직만 있어야 한다.
 * 다른 상태나 객체값을 변화시키지 않도록 조심하자.
 * Mediator 의 목적은 한 State 가 다른 State 들에게 의존하지 않도록 중재인 역할을 하는데 목적이 있다.
 * 따라서, State 는 Colleague 로써 다른 State 에 의존성을 가져서는 안된다.
 */
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
