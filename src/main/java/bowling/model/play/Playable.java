package bowling.model.play;

import bowling.model.Pin;

public interface Playable {

	Play play(Pin pin);

	Pin findPin(int index);

	String getGameStatus();

	boolean isStrike();

	boolean isSpare();

	boolean isMiss();

	boolean isGameEnd();

	int calculateFrame(Playable beforeResult);

	int calculateDouble(Playable beforeResult);

	int getTotalScore();

}
