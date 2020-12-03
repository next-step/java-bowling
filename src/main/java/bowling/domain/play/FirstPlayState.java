package bowling.domain.play;

public class FirstPlayState extends NormalPlayState {
    FirstPlayState() {
        super(0);
    }

    @Override
    public boolean isContinue(PlayStatus context, int frameNo) {
        int countOfPins = context.getLastCountOfPins();
        increaseCountOfPins(countOfPins);
        boolean isLast = isLast(frameNo);
        boolean isAllPinDown = isAllPinDown();
        PlayState nextState = isLast && isAllPinDown
                ? BonusPlayState.getInstance()
                : isAllPinDown
                ? InitialPlayState.getInstance()
                : new SecondPlayState(countOfPins);
        context.setState(nextState);
        return !isAllPinDown || isLast;
    }
}
