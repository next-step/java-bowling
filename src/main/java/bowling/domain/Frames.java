package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Frames {

	private static final int MIN_INDEX = 0;
	private static final int LAST_FRAME_INDEX = 9;
	private static final int LAST_NORMAL_FRAME_INDEX = 8;

	private final List<Frame> values;

	public Frames() {
		final Stream<NormalFrame> normalFrameStream = IntStream.rangeClosed(MIN_INDEX, LAST_NORMAL_FRAME_INDEX)
			.mapToObj(i -> NormalFrame.of());
		final Stream<LastFrame> lastFrameStream = Stream.of(LastFrame.of());

		this.values = Stream.concat(normalFrameStream, lastFrameStream)
			.collect(Collectors.toList());
	}

	public Frames(final List<Frame> values) {
		this.values = values;
	}

	public boolean isEnd() {
		return values.get(LAST_FRAME_INDEX).isEnd();
	}

	public Frame current() {
		return values.get(currentIndex());
	}

	public int currentIndex() {
		return IntStream.rangeClosed(MIN_INDEX, LAST_FRAME_INDEX)
			.filter(i -> !values.get(i).isEnd())
			.findFirst()
			.orElseThrow(RuntimeException::new);
	}

	public void pitch(final Pitch result) {
		final Frame current = current();
		current.pitch(result.getPinCount());
	}

	public List<Frame> getValues() {
		return Collections.unmodifiableList(values);
	}
}
