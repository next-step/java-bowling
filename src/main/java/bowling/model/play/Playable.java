package bowling.model.play;

import java.util.List;

import bowling.model.Pin;

public interface Playable {

	List<Pin> play(Pin pin);

	int countGame();

	boolean isStrike();

	boolean isSpare();

	boolean isGameEnd();

}
