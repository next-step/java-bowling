package bowling.domain.state;

import bowling.domain.Score;

public class Ready extends Playing {

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public State bowl(Score score) {
        if (score.isStrike()) {
            return new Strike();
        }
        return new NextBowl(score);
    }

    @Override
    public int getScore() {
        return super.getScore();
    }
}
