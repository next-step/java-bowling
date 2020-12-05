package bowling.domain.bowl;

public class SecondBowlState extends NormalBowlState {
    SecondBowlState(NormalBowlState state) {
        super(state);
    }

    @Override
    public boolean isPlayable(Bowl context, int frameNo) {
        increaseCountOfPins(context.getLastPin());
        boolean isLast = isLast(frameNo);
        BowlState nextState = isLast
                ? GameOverBowlState.getInstance()
                : ReadyBowlState.getInstance();
        context.setState(nextState);
        return isLast && isAllPinDown();
    }
}
