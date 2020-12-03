package bowling.domain.play;

public class SecondPlayState extends NormalPlayState {
    SecondPlayState(int countOfPins) {
        super(countOfPins);
    }

    @Override
    public boolean isContinue(PlayStatus context, int frameNo) {
        increaseCountOfPins(context.getLastCountOfPins());
        boolean isLast = isLast(frameNo);
        PlayState nextState = isLast
                ? GameOverPlayState.getInstance()
                : InitialPlayState.getInstance();
        context.setState(nextState);
        return isLast && isAllPinDown();
    }
}
