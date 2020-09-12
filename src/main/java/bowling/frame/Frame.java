package bowling.frame;

import java.util.List;

import bowling.pin.Pins;

public interface Frame {
	void reflect(Pins knockingDownPins);

	boolean finish();

	List<String> getKnockingDownPinsSigns();
}
