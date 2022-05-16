package bowling.domain.pin;

import bowling.domain.frameresult.FrameResult;

public interface PinNumbers {

     boolean isFull();

     void addPin(int pinNo);

     String toExpression();

     FrameResult getResult();

     int spareBonus();

     int strikeBonus();
}
