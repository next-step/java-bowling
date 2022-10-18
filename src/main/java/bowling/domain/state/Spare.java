package bowling.domain.state;

import bowling.domain.Pins;

public class Spare extends Finished {


    public Spare(Pins pins) {
        super(pins);
        if(!pins.isSpare()){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int bonusCount() {
        return 1;
    }

    @Override
    public boolean canGetBonus() {
        return true;
    }

}
