package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores {

	private final List<Score> scores;

	public Scores(List<Score> scores) {
		this.scores = new ArrayList<>(scores);
	}

	public static Scores from(Frames frames) {
		return frames.scores();
	}

	public Score scoreOf(int frameNumber) {
		return scores.get(frameNumber - 1);
	}

	public List<Score> scores() {
		return Collections.unmodifiableList(scores);
	}
}
