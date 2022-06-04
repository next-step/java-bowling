package bowling.frame;

import java.util.Optional;

import bowling.score.Score;

public interface Frame {

	int MIN_FRAME_NUMBER = 1;
	int MAX_FRAME_NUMBER = 10;

	boolean isEnd();

	void throwBowl(int throwCount);

	Optional<Frame> nextFrame();

	int number();

	String symbol();

	default boolean canScore() {
		return score().canScore();
	}

	Score score();

	Score bonus(Score previousScore);
}
