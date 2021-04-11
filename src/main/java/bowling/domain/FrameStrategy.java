package bowling.domain;

import java.util.List;

public interface FrameStrategy {
    List<PinNumber> getPinNumbers();
    void play(PinNumber pinNumber);
    String state(int index);
}
