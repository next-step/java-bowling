package bowling.frame;

import bowling.HitNumber;

public interface Frame {
    Frame next(int index);
    Frame roll(HitNumber rollNumber);
    boolean isFinished();
}
