package bowling.domain.play;

class NormalPlayState extends PlayState {
    private NormalPlayState() {}

    static NormalPlayState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    void playFirst(PlayContext context) {
        context.execute();
        if (isStrike(context)) {
            context.setState(StrikePlayState.getInstance());
        }
    }

    @Override
    void playSecond(PlayContext context, int frameNo) {
        context.execute();
        if (isLast(frameNo) && isSpare(context)) {
            context.setState(LastSparePlayState.getInstance());
            return;
        }
        if (isLast(frameNo)) {
            context.setState(GameOverPlayState.getInstance());
            return;
        }
    }

    @Override
    public void playBonus(PlayContext context) {}

    private static class SingletonHelper {
        private static final NormalPlayState instance = new NormalPlayState();
    }
}
