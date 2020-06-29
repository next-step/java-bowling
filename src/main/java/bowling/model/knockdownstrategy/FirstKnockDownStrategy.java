package bowling.model.knockdownstrategy;

import bowling.model.KnockedDownPins;

public class FirstKnockDownStrategy implements KnockDownStrategy {

  @Override
  public KnockedDownPins knockDown(int numberOfKnockedDown) {
    return KnockedDownPins.getBuilder(numberOfKnockedDown).build();
  }
}
