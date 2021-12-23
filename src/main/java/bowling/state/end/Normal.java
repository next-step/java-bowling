package bowling.state.end;

import bowling.Pin;

public class Normal implements EndState {

    private final Pin hitPin;

    public Normal(Pin hitPin) {
        this.hitPin = hitPin;
    }

    @Override
    public String getPrintMark() {
        return String.valueOf(hitPin.getHitCount());
    }
}
