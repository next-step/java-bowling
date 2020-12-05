package bowling.domain.scores;

class EmptyScoresState extends ScoresState {
    private EmptyScoresState() {}

    static EmptyScoresState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    void accumulate(Scores scores, Score score) {
        scores.addScore(score);
        scores.setState(NormalScoresState.getInstance());
    }

    private static class SingletonHelper {
        private static final EmptyScoresState instance = new EmptyScoresState();
    }
}
