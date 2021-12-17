package bowling.domain.state;

import bowling.domain.Frame;
import bowling.domain.Pins;

public class End implements State {

    @Override
    public void pitch(Pins existPins, Pins fallDownPins, Frame frame) {
        throw new IllegalArgumentException("종료된 프레임 입니다.");
    }

    @Override
    public boolean progressing() {
        return false;
    }

    @Override
    public String toString() {
        return "End{}";
    }
}
