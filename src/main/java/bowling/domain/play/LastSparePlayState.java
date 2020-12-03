package bowling.domain.play;

class LastSparePlayState implements PlayState {
    private LastSparePlayState() {}

    static LastSparePlayState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void playFirst(PlayContext context, int frameNo) {}

    @Override
    public void playSecond(PlayContext context, int frameNo) {}

    @Override
    public void playBonus(PlayContext context, int frameNo) {
        context.execute();
        PlayMediator.notifyBonus(context, frameNo);
    }

    private static class SingletonHelper {
        private static final LastSparePlayState instance = new LastSparePlayState();
    }
}
