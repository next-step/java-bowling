package bowling.model;

public class SecondKnockDownStrategy implements KnockDownStrategy {

  private final KnockedDownPins knockedDownPins;

  public SecondKnockDownStrategy(KnockedDownPins knockedDownPins) {
    this.knockedDownPins = knockedDownPins;
  }

  @Override
  public KnockedDownPins knockDown(int numberOfKnockedDown) {
    return KnockedDownPins.getBuilder(knockedDownPins.getFirstKnockDownNum())
        .secondKnockDownNum(numberOfKnockedDown)
        .build();
  }
}
