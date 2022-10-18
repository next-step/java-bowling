package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Pins;


public class Strike extends Finished {

    public Strike(Pin pin) {
        super(Pins.of(pin));

        if(!pin.isStrike()){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int bonusCount() {
        return 2;
    }

    @Override
    public boolean canGetBonus() {
        return true;
    }


}
