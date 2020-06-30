package bowling.domain.frame;

import static bowling.util.FrameSize.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Frames {

	private List<Frame> frameList;

	private Frames(List<Frame> frameList) {
		this.frameList = frameList;
	}

	public static Frames of() {
		List<Frame> frameList = createFrameList();
		return new Frames(frameList);
	}

	public static List<Frame> createFrameList() {
		List<Frame> frameList = new ArrayList<>();
		frameList.add(NormalFrame.createFirstFrame());
		IntStream.range(STARTING_INDEX, NORMAL_FRAME_SIZE)
			.mapToObj(range -> frameList.get(range).addNextFrame())
			.forEach(frameList::add);
		return frameList;
	}

	public Frame findPlayingFrame() {
		return frameList.stream()
			.filter(Frame::canPlayMore)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("there is no running frame."));
	}

	public List<Frame> getFrameList() {
		return Collections.unmodifiableList(frameList);
	}
}
