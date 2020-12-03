package bowling.domain.play;

class NormalPlayState extends PlayState {
    private NormalPlayState() {}

    static NormalPlayState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    void playFirst(PlayContext context) {
        context.execute();
        if (isAllPinDown(context, 1)) {
            context.setState(StrikePlayState.getInstance());
        }
    }

    @Override
    void playSecond(PlayContext context, int frameNo) {
        context.execute();
        boolean isLast = isLast(frameNo);
        if (isLast && isAllPinDown(context, 2)) {
            context.setState(LastSparePlayState.getInstance());
            return;
        }
        if (isLast) {
            context.setState(GameOverPlayState.getInstance());
        }
    }

    @Override
    void playBonus(PlayContext context) {}

    private static class SingletonHelper {
        private static final NormalPlayState instance = new NormalPlayState();
    }
}
