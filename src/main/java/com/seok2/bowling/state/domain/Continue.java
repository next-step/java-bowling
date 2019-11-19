package com.seok2.bowling.state.domain;

import com.seok2.bowling.pin.domain.Pin;

public class Continue extends Running {

    private final Pin first;

    private Continue(Pin first) {
        this.first = first;
    }

    public static State of(Pin felled) {
        return new Continue(felled);
    }

    @Override
    public State roll(Pin felled) {
        Pin sum = first.add(felled);
        if (sum.isAllFelled()) {
            return Spare.of(first);
        }
        if (sum.isFelledAtAll()) {
            return Gutter.of();
        }
        return Miss.of(first, felled);
    }

    @Override
    public String view() {
        return first.toString();
    }
}
