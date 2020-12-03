package bowling.domain.play;

class LastStrikePlayState implements PlayState {
    private LastStrikePlayState() {}

    static LastStrikePlayState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void playFirst(PlayContext context, int frameNo) {}

    @Override
    public void playSecond(PlayContext context, int frameNo) {}

    @Override
    public void playBonus(PlayContext context, int frameNo) {
        context.execute();
        context.execute();
        PlayMediator.notifyBonus(context, frameNo);
    }

    private static class SingletonHelper {
        private static final LastStrikePlayState instance = new LastStrikePlayState();
    }
}
