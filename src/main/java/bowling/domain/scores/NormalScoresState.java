package bowling.domain.scores;

import bowling.domain.Score;

public class NormalScoresState implements ScoresState {
    private NormalScoresState() {}

    static NormalScoresState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void accumulate(ScoresContext context, Score score) {
        context.addScore(
                context.getLast().sum(score)
        );
    }

    private static class SingletonHelper {
        private static final NormalScoresState instance = new NormalScoresState();
    }
}
