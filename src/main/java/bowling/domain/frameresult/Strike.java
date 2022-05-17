package bowling.domain.frameresult;

import static bowling.domain.pin.PinNo.MAX_PIN_NO;

public class Strike implements FrameResult {

    private Integer bonus;

    @Override
    public int calculateScore() {
        return MAX_PIN_NO + bonus;
    }

    @Override
    public boolean isCalculated() {
        return bonus != null;
    }

    @Override
    public void addBonus(int bonus) {
        if (this.bonus == null) {
            this.bonus = 0;
        }
        this.bonus += bonus;
    }
}
