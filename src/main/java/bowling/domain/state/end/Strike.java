package bowling.domain.state.end;

import bowling.Pin;

/**
 * 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
 *
 * @apiNote FinalFrame에서는 한번 더 시도할 수 있음.
 */
public class Strike extends PinEndState implements BonusAbleState {

    private static final String MARKER = "X";

    public Strike(Pin pin) {
        super(pin);

        if (!pin.isStrike()) {
            throw new IllegalArgumentException("Strike가 아니에요.");
        }
    }

    @Override
    public String getPrintMark() {
        return MARKER;
    }

    @Override
    public boolean isInstanceOf(Class<? extends EndState> clazz) {
        return clazz.isInstance(this);
    }
}
