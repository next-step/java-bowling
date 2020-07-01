package bowling;

import bowling.domain.frame.Frames;
import bowling.domain.score.Score;

public class BowlingGame {

	public Frames startGame() {
		return Frames.of();
	}

	public Frames addScore(Frames frames, Score score) {
		frames.addScore(score);
		return frames;
	}
}
