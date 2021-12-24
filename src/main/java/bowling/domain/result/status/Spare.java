package bowling.domain.result.status;

import bowling.domain.Pin;
import bowling.domain.result.ResultState;

/**
 * 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
 *
 * @apiNote FinalFrame에서는, 한번 더 시도할 수 있음.
 */
public class Spare extends PinResultState implements BonusAbleState {

    private static final String MARKER = "/";

    public Spare(Pin pin) {
        super(pin);
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
