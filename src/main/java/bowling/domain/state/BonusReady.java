package bowling.domain.state;

import bowling.domain.Hit;

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
    public int bowlingCount() {
        return 0;
    }

    @Override
    public String description() {
        return "";
    }
}
