package bowling.model.play;

import bowling.model.Pin;

public interface Playable {

	Play play(Pin pin);

	int countGame();

	Pin findPin(int index);

	boolean isStrike();

	boolean isSpare();

	boolean isMiss();

	boolean isGameEnd();

}
