package bowling.step2.domain;

import java.util.List;

public interface Frame {
    void pitch(TryNo tryNo, int count);
    int total();
    int countOfFirst();
    int countOfSecond();
    Frame nextFrame();
}
