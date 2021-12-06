package bowling.domain.frame;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import bowling.domain.Index;
import bowling.domain.Pins;

public class Frames {
	private static final String EMPTY_MESSAGE = "프레임 List 가 비어있습니다.";
	private static final String INVALID_INDEX_MESSAGE = "생성 되지 않은 인덱스 입니다.";

	private final LinkedList<Frame> values;

	private Frames(LinkedList<Frame> values) {
		validationSize(values);
		this.values = values;
	}

	private void validationSize(LinkedList<Frame> values) {
		if (values == null || values.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_MESSAGE);
		}
	}

	public static Frames initialize() {
		return new Frames(new LinkedList<>(Collections.singletonList(NormalFrame.initialize())));
	}

	public void bowl(Pins pins) {
		Frame lastFrame = values.getLast();
		Frame resultFrame = lastFrame.bowl(pins);

		if (isFrameCreated(lastFrame, resultFrame)) {
			values.add(resultFrame);
		}
	}

	private boolean isFrameCreated(Frame lastFrame, Frame resultFrame) {
		return lastFrame != resultFrame;
	}

	public int getLastFrameIndex() {
		return values.getLast().getFrameIndex();
	}

	public boolean hasNextPitching() {
		if (values.size() < Index.MAX_OF_INDEX) {
			return true;
		}
		return !values.getLast().isEnd();
	}

	public boolean isFrameRunning(Index index) {
		try {
			Frame frame = values.get(index.getValue() - 1);
			return !frame.isEnd();
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException(INVALID_INDEX_MESSAGE);
		}
	}

	public List<Frame> getValues() {
		return Collections.unmodifiableList(values);
	}
}
