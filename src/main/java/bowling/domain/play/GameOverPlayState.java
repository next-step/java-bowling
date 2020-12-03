package bowling.domain.play;

// NOTE: 더이상 play 하지 않는 상태를 {} 로 표현
class GameOverPlayState implements PlayState {
    private GameOverPlayState() {}

    static GameOverPlayState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void playFirst(PlayContext context, int frameNo) {}

    @Override
    public void playSecond(PlayContext context, int frameNo) {}

    @Override
    public void playBonus(PlayContext context, int frameNo) {}

    private static class SingletonHelper {
        private static final GameOverPlayState instance = new GameOverPlayState();
    }
}
