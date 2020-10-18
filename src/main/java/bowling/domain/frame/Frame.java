package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;

import java.util.List;

public interface Frame {
    void roll(Pin pin);

    int getTotal();

    boolean canRoll();

    int getIndex();

    List<Pin> getPinInfo();

    Pins getPins();

    String getStringScore();

    void addScore();

    boolean isGameOver();

    boolean hasScore();

    void calculateScore(int pins);
}
