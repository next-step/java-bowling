package bowling.domain.play;

// NOTE: 더이상 play 하지 않는 상태를 {} 로 표현
class GameOverPlayState extends PlayState {
    private GameOverPlayState() {}

    static GameOverPlayState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    void playFirst(PlayContext context) {}

    @Override
    void playSecond(PlayContext context, int frameNo) {}

    @Override
    void playBonus(PlayContext context) {}

    private static class SingletonHelper {
        private static final GameOverPlayState instance = new GameOverPlayState();
    }
}
