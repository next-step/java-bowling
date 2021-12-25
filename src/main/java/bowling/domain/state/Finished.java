package bowling.domain.state;

import bowling.domain.KnockedPinCount;

import java.util.List;

public interface Finished {
    List<KnockedPinCount> getValues();
}
