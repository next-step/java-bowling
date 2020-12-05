package bowling.domain.bowl;

public class FirstBowlState extends NormalBowlState {
    @Override
    public boolean isPlayable(Bowl context, int frameNo) {
        increaseCountOfPins(context.getLastPin());
        boolean isLast = isLast(frameNo);
        boolean isAllPinDown = isAllPinDown();
        BowlState nextState = isLast && isAllPinDown
                ? BonusBowlState.getInstance()
                : isAllPinDown
                ? ReadyBowlState.getInstance()
                : new SecondBowlState(this);
        context.setState(nextState);
        return !isAllPinDown || isLast;
    }
}
