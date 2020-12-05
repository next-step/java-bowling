package bowling.domain.bowl;

class BonusBowlState implements BowlState {

    private BonusBowlState() {}

    static BonusBowlState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public boolean isPlayable(Bowl context, int frameNo) {
        context.setState(GameOverBowlState.getInstance());
        return true;
    }

    private static class SingletonHelper {
        private static final BonusBowlState instance = new BonusBowlState();
    }
}
