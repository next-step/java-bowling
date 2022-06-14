package bowling.domain.state;

import bowling.domain.Hit;
import bowling.domain.Score;
import bowling.exception.CannotCalculateScore;

public class Ready extends Ongoing {

    @Override
    public State bowl(int hit) {
        Hit firstHit = Hit.valueOf(hit);
        if (firstHit.isMaximum()) {
            return new Strike();
        }
        return new FirstHit(firstHit);
    }

    @Override
    public boolean isProgressing() {
        return false;
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        throw new CannotCalculateScore();
    }

    @Override
    public int bowlingCount() {
        return NO_HIT;
    }
}
