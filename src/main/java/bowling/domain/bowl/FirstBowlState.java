package bowling.domain.bowl;

public class FirstBowlState extends NormalBowlState {
    @Override
    public boolean isPlayable(Bowl bowl, int frameNo) {
        increaseCountOfPins(bowl.getLastPin());
        boolean isLast = isLast(frameNo);
        boolean isAllPinDown = isAllPinDown();
        BowlState nextState = isLast && isAllPinDown
                ? BonusBowlState.getInstance()
                : isAllPinDown
                ? ReadyBowlState.getInstance()
                : new SecondBowlState(this);
        bowl.setState(nextState);
        return !isAllPinDown || isLast;
    }
}
