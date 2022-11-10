package bowling.domain.status;

import bowling.domain.Pin;

public class Miss extends Finished {

    public Miss(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }
}
