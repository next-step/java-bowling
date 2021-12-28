package bowling.domain.result.status;

import bowling.domain.Pin;
import bowling.domain.result.ResultState;

/**
 * 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
 */
public class Gutter extends PinResultState implements NextAbleState {

    private static final String MARKER = "-";

    public Gutter(Pin pin) {
        super(pin);

        if (!pin.isGutter()) {
            throw new IllegalArgumentException("Gutter이 아니에요.");
        }
    }

    @Override
    public String getPrintMark() {
        return MARKER;
    }

    @Override
    public boolean isInstanceOf(Class<? extends ResultState> clazz) {
        return clazz.isInstance(this);
    }
}
