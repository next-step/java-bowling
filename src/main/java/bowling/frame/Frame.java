package bowling.frame;

import java.util.Optional;

public interface Frame {

	int MIN_FRAME_NUMBER = 1;
	int MAX_FRAME_NUMBER = 10;

	boolean isEnd();

	void throwBowl(int throwCount);

	Optional<Frame> nextFrame();

	int number();

	String symbol();
}
