package bowling.model.knockdownstrategy;

import bowling.model.KnockedDownPins;

public interface KnockDownStrategy {

  KnockedDownPins knockDown(int numberOfKnockedDown);
}
