package bowling.domain.state;

import bowling.domain.Score;

public class Ready extends Playing {

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public String display() {
        return super.display();
    }

    @Override
    public State bowl(Score score) {
        if(score.isStrike(score)){
            return new Strike();
        }
        return new NextBowl(score);
    }
}
