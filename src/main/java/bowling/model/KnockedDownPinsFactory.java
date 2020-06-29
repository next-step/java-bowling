package bowling.model;

import bowling.model.knockdownstrategy.FirstKnockDownStrategy;
import bowling.model.knockdownstrategy.SecondKnockDownStrategy;
import bowling.model.knockdownstrategy.StrikeKnockDownStrategy;

public class KnockedDownPinsFactory {

  private KnockedDownPinsFactory() {
  }

  public static KnockedDownPins createInstanceBy(KnockedDownPins pins, int knockDownNum) {
    if (pins.isFirstKnockDownNumNull() && knockDownNum == KnockedDownPins.MAX_NUMBER_OF_PINS) {
      return new StrikeKnockDownStrategy().knockDown(knockDownNum);
    }

    if (pins.isFirstKnockDownNumNull()) {
      return new FirstKnockDownStrategy().knockDown(knockDownNum);
    }

    return new SecondKnockDownStrategy(pins).knockDown(knockDownNum);
  }
}
