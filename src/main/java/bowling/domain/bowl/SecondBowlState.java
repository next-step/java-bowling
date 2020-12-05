package bowling.domain.bowl;

public class SecondBowlState extends NormalBowlState {
    SecondBowlState(NormalBowlState state) {
        super(state);
    }

    @Override
    public boolean isPlayable(Bowl bowl, int frameNo) {
        increaseCountOfPins(bowl.getLastPin());
        boolean isLast = isLast(frameNo);
        BowlState nextState = isLast
                ? GameOverBowlState.getInstance()
                : ReadyBowlState.getInstance();
        bowl.setState(nextState);
        return isLast && isAllPinDown();
    }
}
