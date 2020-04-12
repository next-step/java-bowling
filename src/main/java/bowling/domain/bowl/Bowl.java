package bowling.domain.bowl;

import bowling.domain.pin.BowlCount;

public interface Bowl {
    Bowl bowl(BowlCount bowlCount);
}
