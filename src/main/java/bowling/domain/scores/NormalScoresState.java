package bowling.domain.scores;

class NormalScoresState extends ScoresState {
    private NormalScoresState() {}

    static NormalScoresState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    void accumulate(Scores context, Score score) {
        context.addScore(
                context.getLast().sum(score)
        );
    }

    private static class SingletonHelper {
        private static final NormalScoresState instance = new NormalScoresState();
    }
}
