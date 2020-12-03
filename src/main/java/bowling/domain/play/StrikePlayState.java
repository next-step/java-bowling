package bowling.domain.play;

class StrikePlayState extends PlayState {
    private StrikePlayState() {}

    static StrikePlayState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    void playFirst(PlayContext context) {
        context.execute();
        if (!isAllPinDown(context, 1)) {
            context.setState(NormalPlayState.getInstance());
        }
    }

    @Override
    void playSecond(PlayContext context, int frameNo) {
        if (isLast(frameNo)) {
            context.setState(LastStrikePlayState.getInstance());
        }
    }

    @Override
    void playBonus(PlayContext context) {}

    private static class SingletonHelper {
        private static final StrikePlayState instance = new StrikePlayState();
    }
}
