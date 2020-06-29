package bowling.model;

public class StrikeKnockDownStrategy implements KnockDownStrategy {

  @Override
  public KnockedDownPins knockDown(int numberOfKnockedDown) {
    if (numberOfKnockedDown != 10) {
      throw new IllegalArgumentException(
          "스트라이크가 아닙니다. numberOfKnockedDown : " + numberOfKnockedDown);
    }

    return KnockedDownPins.getBuilder(numberOfKnockedDown)
        .secondKnockDownNum(0)
        .build();
  }
}
