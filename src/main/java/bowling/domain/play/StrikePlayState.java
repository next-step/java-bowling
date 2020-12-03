package bowling.domain.play;

public class StrikePlayState implements PlayState {
    private StrikePlayState() {}

    static StrikePlayState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void playFirst(PlayContext context, int frameNo) {
        context.execute();
        PlayMediator.notifyFirst(context, frameNo);
    }

    @Override
    public void playSecond(PlayContext context, int frameNo) {}

    @Override
    public void playBonus(PlayContext context, int frameNo) {}

    private static class SingletonHelper {
        private static final StrikePlayState instance = new StrikePlayState();
    }
}
