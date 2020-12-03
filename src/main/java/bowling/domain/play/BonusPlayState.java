package bowling.domain.play;

class BonusPlayState implements PlayState {

    private BonusPlayState() {}

    static BonusPlayState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public boolean isContinue(PlayStatus context, int frameNo) {
        context.setState(GameOverPlayState.getInstance());
        return true;
    }

    private static class SingletonHelper {
        private static final BonusPlayState instance = new BonusPlayState();
    }
}
