package bowling.domain.status;

import bowling.domain.Pin;

public class Spare extends Finished {

    public Spare(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

}
