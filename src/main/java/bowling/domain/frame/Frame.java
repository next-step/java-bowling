package bowling.domain.frame;

import bowling.domain.HitNumber;

public interface Frame {
    Frame next(int index);
    Frame roll(HitNumber rollNumber);
    boolean isFinished();
}
