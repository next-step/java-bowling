package bowling.domain.state;

import bowling.domain.Frame;
import bowling.domain.Pitch;

public class End implements State {

    @Override
    public void run(Pitch pitch, Frame frame) {
        throw new IllegalArgumentException("종료된 프레임 입니다.");
    }

    @Override
    public boolean progressing() {
        return false;
    }

    @Override
    public boolean retryable() {
        return false;
    }

    @Override
    public String toString() {
        return "End{}";
    }
}
