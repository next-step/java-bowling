package bowling.model.play;

import java.util.List;

import bowling.model.Pin;

public interface Playable {

	List<Pin> play(Pin pin);

	boolean isFirstStrike();

	boolean isNotStrikeOrSpare();

	boolean isGameEnd();

}
