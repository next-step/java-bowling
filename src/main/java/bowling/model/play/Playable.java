package bowling.model.play;

import bowling.model.Pin;
import bowling.model.score.Score;

public interface Playable {

	Score play(Pin pin);

	Pin findPin(int index);

	String getGameStatus();

	boolean isStrike();

	boolean isSpare();

	boolean isMiss();

	boolean isGameEnd();

}
