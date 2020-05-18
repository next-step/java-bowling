package bowling.domain.rolling;

import java.util.List;

public interface Rollings {
    void roll(int pinCount);
    boolean isRollingPossible();
    List<String> getStates();
}
