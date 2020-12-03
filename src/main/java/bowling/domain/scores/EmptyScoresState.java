package bowling.domain.scores;

class EmptyScoresState extends ScoresState {
    private EmptyScoresState() {}

    static EmptyScoresState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    void accumulate(Scores context, Score score) {
        context.addScore(score);
        context.setState(NormalScoresState.getInstance());
    }

    private static class SingletonHelper {
        private static final EmptyScoresState instance = new EmptyScoresState();
    }
}
