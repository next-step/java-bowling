package bowling.domain.play;

class NormalPlayState implements PlayState {
    private NormalPlayState() {}

    static NormalPlayState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void playFirst(PlayContext context, int frameNo) {
        context.execute();
        PlayMediator.notifyFirst(context, frameNo);
    }

    @Override
    public void playSecond(PlayContext context, int frameNo) {
        context.execute();
        PlayMediator.notifySecond(context, frameNo);
    }

    @Override
    public void playBonus(PlayContext context, int frameNo) {}

    private static class SingletonHelper {
        private static final NormalPlayState instance = new NormalPlayState();
    }
}
