package bowling.model;

import bowling.model.knockdownstrategy.FirstKnockDownStrategy;
import bowling.model.knockdownstrategy.SecondKnockDownStrategy;

public class KnockedDownPinsFactory {

  private KnockedDownPinsFactory() {
  }

  public static KnockedDownPins createInstanceBy(KnockedDownPins pins, int knockDownNum) {
    if (pins.isFirstKnockDownNumberNull()) {
      return new FirstKnockDownStrategy().knockDown(knockDownNum);
    }

    return new SecondKnockDownStrategy(pins).knockDown(knockDownNum);
  }
}
