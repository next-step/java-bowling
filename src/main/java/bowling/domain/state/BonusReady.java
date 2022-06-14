package bowling.domain.state;

import bowling.domain.Hit;
import bowling.domain.Score;
import bowling.exception.CannotCalculateScore;

public class BonusReady extends Ongoing {

    @Override
    public State bowl(int hit) {
        Hit firstHit = Hit.valueOf(hit);
        if (firstHit.isMaximum()) {
            return new Strike();
        }
        return new BonusHit(firstHit);
    }

    @Override
    public boolean isProgressing() {
        return true;
    }

    @Override
    public int bowlingCount() {
        return 0;
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        throw new CannotCalculateScore();
    }
}
