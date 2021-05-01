package bowling.domain.state;

import bowling.domain.HitCount;

public final class Ready extends Running {

    private Pins pins;

    private Ready() {
        this.pins = Pins.initialize();
    }

    @Override
    public final State bowl(final HitCount hitCount) {
        pins = pins.hit(hitCount);
        if (pins.isEmpty()) {
            return Strike.initialize();
        }
        return FirstBowl.from(hitCount.count());
    }

    public static final State initialize() {
        return new Ready();
    }

}
