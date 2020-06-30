package bowling.model.knockdownstrategy;

import bowling.model.KnockedDownPins;

public class SecondKnockDownStrategy implements KnockDownStrategy {

  private final KnockedDownPins knockedDownPins;

  public SecondKnockDownStrategy(KnockedDownPins knockedDownPins) {
    this.knockedDownPins = knockedDownPins;
  }

  @Override
  public KnockedDownPins knockDown(int numberOfKnockedDown) {
    return KnockedDownPins.getBuilder(knockedDownPins.getFirstKnockDownNumber())
        .secondKnockDownNumber(numberOfKnockedDown)
        .build();
  }
}
