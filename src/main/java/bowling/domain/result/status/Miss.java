package bowling.domain.result.status;

import bowling.domain.Pin;
import bowling.domain.result.ResultState;

/**
 * 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
 */
public class Miss extends PinResultState {

    private static final String MARKER = "-";

    public Miss(Pin pin) {
        super(pin);

        if (!pin.isGutter()) {
            throw new IllegalArgumentException("Miss가 올 수 없어요.");
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
