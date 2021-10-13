package step4.domain.state;

import step4.domain.Score;

abstract class Finished implements State {
    private Score score;

    @Override
    public State throwBowl(int fallenPins) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public Score score() {
        return score;
    }

    public int getScore() {
        return score.getScore();
    }
}
