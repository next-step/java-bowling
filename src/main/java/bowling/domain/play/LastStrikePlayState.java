package bowling.domain.play;

class LastStrikePlayState extends PlayState {
    private LastStrikePlayState() {}

    static LastStrikePlayState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    void playFirst(PlayContext context) {}

    @Override
    void playSecond(PlayContext context, int frameNo) {}

    @Override
    void playBonus(PlayContext context) {
        context.execute();
        context.execute();
        context.setState(GameOverPlayState.getInstance());
    }

    private static class SingletonHelper {
        private static final LastStrikePlayState instance = new LastStrikePlayState();
    }
}
