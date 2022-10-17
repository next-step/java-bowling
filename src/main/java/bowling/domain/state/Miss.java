package bowling.domain.state;

import bowling.domain.Pins;

public class Miss extends Finished {

    public Miss(Pins pins) {
        super(pins);
        if(!pins.isMiss()){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int bonusCount() {
        return 0;
    }

    @Override
    public boolean canGetBonus() {
        return false;
    }


}
