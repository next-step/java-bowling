package bowling.model.play;

import java.util.List;

import bowling.model.Pin;

public interface Playable {

	List<Pin> play(Pin pin);

	int countGame();

	Pin findPin(int index);

	boolean isStrike();

	boolean isSpare();

	boolean isMiss();

	boolean isGameEnd();

}
