package bowling.bowl;

import bowling.pin.Pins;

public interface Bowl {

    boolean isEnd();

    Bowl pitch(Pins pins);

    boolean isStrike();

    boolean isSpare();

    boolean isMiss();

    boolean isGutter();

    boolean isFirst();

    boolean isSecond();

    String getSymbol();
}
