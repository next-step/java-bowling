package bowling.domain.state;

import bowling.domain.pin.Pins;

import java.util.List;

public interface CommonState extends ResultState {

    CommonState hitPins(Pins pins);
    List<Integer> getHittedPins();

}
