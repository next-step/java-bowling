package bowling.domain.interfaces;

import bowling.domain.Pins;

import java.util.List;

public interface Frame {

    Frame bowl(int count);

    boolean isGameEnd();

    Frame getNextFrame();

    List<Pins> getPinsList();
}
