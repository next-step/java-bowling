package bowling;

public class FirstKnockDownStrategy implements KnockDownStrategy {

  @Override
  public KnockedDownPins knockDown(int numberOfKnockedDown) {
    return KnockedDownPins.getBuilder(numberOfKnockedDown).build();
  }
}
