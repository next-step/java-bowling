package bowling.domain.state;

import bowling.domain.Pins;

public class Strike implements FrameState {
    private static final String STRIKE_SYMBOL = "X";

    @Override
    public FrameState bowl(Pins hitPins) {
        throw new IllegalArgumentException("프레임의 투구가 완료된 상태에서 볼을 굴릴 수 없습니다.");
    }

    @Override
    public boolean isFrameEnd() {
        return true;
    }

    @Override
    public String symbol() {
        return STRIKE_SYMBOL;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }
}