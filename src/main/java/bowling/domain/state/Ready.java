package bowling.domain.state;

import bowling.domain.HitCount;

public class Ready extends Running{

    @Override
    public State bowl(HitCount hitCount) {
        if(hitCount.count() == 10) {
            return new Strike();
        }
        return new FirstBowl(hitCount.count());
    }


}
