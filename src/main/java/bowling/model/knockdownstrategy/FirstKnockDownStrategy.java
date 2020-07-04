package bowling.model.knockdownstrategy;

import bowling.model.KnockedDownPins;

public class FirstKnockDownStrategy implements KnockDownStrategy {

  @Override
  public KnockedDownPins knockDown(int numberOfKnockedDown) {
    if (numberOfKnockedDown == 10) {
      return KnockedDownPins.getBuilder()
          .firstKnockDownNumber(numberOfKnockedDown)
          .secondKnockDownNumber(0)
          .build();
    }

    return KnockedDownPins.getBuilder()
        .firstKnockDownNumber(numberOfKnockedDown)
        .build();
  }
}
