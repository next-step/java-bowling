package bowling.domain.scores;

class NormalScoresState extends ScoresState {
    private NormalScoresState() {}

    static NormalScoresState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    void accumulate(Scores scores, Score score) {
        scores.addScore(
                scores.getLast().sum(score)
        );
    }

    private static class SingletonHelper {
        private static final NormalScoresState instance = new NormalScoresState();
    }
}
