package bowling.domain.play;

class LastSparePlayState extends PlayState {
    private LastSparePlayState() {}

    static LastSparePlayState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    void playFirst(PlayContext context) {}

    @Override
    void playSecond(PlayContext context, int frameNo) {}

    @Override
    void playBonus(PlayContext context) {
        context.execute();
        context.setState(GameOverPlayState.getInstance());
    }

    private static class SingletonHelper {
        private static final LastSparePlayState instance = new LastSparePlayState();
    }
}
