package bowling.domain.state;

import bowling.domain.Pins;

public class BeforeProgress implements FrameState {
    private static final String NONE = "";

    @Override
    public FrameState bowl(Pins hitPins) {
        if (hitPins.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(hitPins);
    }

    @Override
    public boolean isFrameEnd() {
        return false;
    }

    @Override
    public String symbol() {
        return NONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}