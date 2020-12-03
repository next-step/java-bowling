package bowling.domain.play;

public class InitialPlayState implements PlayState {
    private InitialPlayState() {}

    static InitialPlayState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public boolean isContinue(PlayStatus context, int frameNo) {
        context.setState(new FirstPlayState());
        return true;
    }

    private static class SingletonHelper {
        private static final InitialPlayState instance = new InitialPlayState();
    }
}
