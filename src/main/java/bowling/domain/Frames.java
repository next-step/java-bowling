package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Frames {

	private static final int MAX = 10;

	private final List<Frame> values;

	public Frames() {
		this.values = new ArrayList<>();
		for (int i = 0; i < MAX - 1; i++) {
			this.values.add(new NormalFrame());
		}

		this.values.add(new LastFrame());
	}

	public Frames(final List<Frame> values) {
		this.values = values;
	}

	public boolean isEnd() {
		return values.get(MAX - 1).isEnd();
	}

	public Frame current() {
		return values.get(currentIndex() - 1);
	}

	public int currentIndex() {
		return IntStream.range(0, MAX)
			.filter(i -> !values.get(i).isEnd())
			.findFirst()
			.orElseThrow(RuntimeException::new) + 1;
	}

	public void pitch(final Pitch result) {
		final Frame current = current();
		current.pitch(result.getPinCount());
	}

	public List<Frame> getValues() {
		return Collections.unmodifiableList(values);
	}
}
