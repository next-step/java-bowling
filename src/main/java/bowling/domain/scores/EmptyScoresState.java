package bowling.domain.scores;

import bowling.domain.Score;

public class EmptyScoresState implements ScoresState {
    private EmptyScoresState() {}

    static EmptyScoresState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public void accumulate(ScoresContext context, Score score) {
        context.addScore(score);
        context.setState(NormalScoresState.getInstance());
    }

    private static class SingletonHelper {
        private static final EmptyScoresState instance = new EmptyScoresState();
    }
}
