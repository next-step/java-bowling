package bowling.frame;

import java.util.List;

import bowling.pin.Pins;
import bowling.score.Score;

public interface Frame {
	Score reflect(Pins knockingDownPins);

	boolean finish();

	List<String> getKnockingDownPinsSigns();
}
