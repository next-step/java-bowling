package bowling.domain.bowl;

public class SecondBowlState extends NormalBowlState {
    SecondBowlState(int countOfPins) {
        super(countOfPins);
    }

    @Override
    public boolean isPlayable(Bowl context, int frameNo) {
        increaseCountOfPins(context.getLastCountOfPins());
        boolean isLast = isLast(frameNo);
        BowlState nextState = isLast
                ? GameOverBowlState.getInstance()
                : ReadyBowlState.getInstance();
        context.setState(nextState);
        return isLast && isAllPinDown();
    }
}
