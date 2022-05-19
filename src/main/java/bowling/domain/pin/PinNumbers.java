package bowling.domain.pin;

import bowling.domain.frameresult.FrameResult;

public interface PinNumbers {

     boolean isFull();

     void addPin(int pinNo);

     String expression();

     FrameResult result();

     int spareBonus();

     int strikeBonus();
}
