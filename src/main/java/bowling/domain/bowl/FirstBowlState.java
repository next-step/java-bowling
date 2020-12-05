package bowling.domain.bowl;

public class FirstBowlState extends NormalBowlState {
    FirstBowlState() {
        super(0);
    }

    @Override
    public boolean isPlayable(Bowl context, int frameNo) {
        int countOfPins = context.getLastCountOfPins();
        increaseCountOfPins(countOfPins);
        boolean isLast = isLast(frameNo);
        boolean isAllPinDown = isAllPinDown();
        BowlState nextState = isLast && isAllPinDown
                ? BonusBowlState.getInstance()
                : isAllPinDown
                ? ReadyBowlState.getInstance()
                : new SecondBowlState(countOfPins);
        context.setState(nextState);
        return !isAllPinDown || isLast;
    }
}
