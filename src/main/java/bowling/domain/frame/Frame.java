package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;

import java.util.List;

public interface Frame {
    void roll(Pin pin);

    int getTotal();

    boolean canRoll();

    List<Pin> getPinInfo();

    Pins getPins();

    void addScore();

    boolean isGameOver();

    boolean hasScore();

    boolean hasRolled();

    void calculateScore(int pins);

    Integer getScore();

}
