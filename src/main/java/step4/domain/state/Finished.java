package step4.domain.state;

import step4.domain.Score;

public abstract class Finished implements State {
    private Score score;

    public Finished(int score, int left) {
        this.score = new Score(10, 2);
    }

    @Override
    public State throwBowl(int fallenPins) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public int getScore() {
        return score.getScore();
    }

    @Override
    public Score score() {
        return score;
    }
}
