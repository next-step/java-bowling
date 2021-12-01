package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bowling.domain.Index;
import bowling.domain.Pins;

public class Frames {
	private static final String EMPTY_MESSAGE = "프레임 List 가 비어있습니다.";

	private final List<Frame> values;

	private Frames(List<Frame> values) {
		validationSize(values);
		this.values = values;
	}

	private void validationSize(List<Frame> values) {
		if (values == null || values.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_MESSAGE);
		}
	}

	public static Frames initialize() {
		return new Frames(new ArrayList<>(Collections.singletonList(NormalFrame.initialize())));
	}

	public void bowl(Pins pins) {
		Frame lastFrame = getLastFrame();
		Frame resultFrame = lastFrame.bowl(pins);

		if (isFrameCreated(lastFrame, resultFrame)) {
			values.add(resultFrame);
		}
	}

	private boolean isFrameCreated(Frame lastFrame, Frame resultFrame) {
		return lastFrame.isEnd() && !resultFrame.isEnd();
	}

	private Frame getLastFrame() {
		return values.get(values.size() - 1);
	}

	public int getLastFrameIndex() {
		Frame lastFrame = getLastFrame();
		return lastFrame.getFrameIndex();
	}

	public boolean hasNext() {
		return !(getLastFrame().isEnd() && values.size() == Index.MAX_OF_INDEX);
	}

	public List<Frame> getValues() {
		return Collections.unmodifiableList(values);
	}
}
