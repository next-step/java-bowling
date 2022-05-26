package bowling.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {

	private final List<Frame> values = new ArrayList<>();

	private Frames(List<Frame> frames) {
		this.values.addAll(frames);
	}

	public static Frames start() {
		return new Frames(List.of(new GeneralFrame()));
	}

	public boolean isEnd() {
		return false;
	}

	public int frameNumber() {
		return 0;
	}

	public void throwBowl(int askThrowCount) {

	}
}
