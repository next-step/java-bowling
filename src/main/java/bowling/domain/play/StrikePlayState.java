package bowling.domain.play;

public class StrikePlayState extends PlayState {
    private StrikePlayState() {}

    static StrikePlayState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void playFirst(PlayContext context) {
        context.execute();
        if (!isStrike(context)) {
            context.setState(NormalPlayState.getInstance());
        }
    }

    @Override
    public void playSecond(PlayContext context, int frameNo) {
        if (isLast(frameNo)) {
            context.setState(LastStrikePlayState.getInstance());
        }
    }

    @Override
    public void playBonus(PlayContext context) {}

    private static class SingletonHelper {
        private static final StrikePlayState instance = new StrikePlayState();
    }
}
