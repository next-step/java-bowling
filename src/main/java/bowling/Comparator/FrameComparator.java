package bowling.Comparator;

import java.util.Comparator;

import bowling.model.frame.Frame;

public class FrameComparator implements Comparator<Frame> {
	@Override
	public int compare(Frame frame, Frame compareFrame) {
		return Integer.compare(frame.getFrameNumber(), compareFrame.getFrameNumber());
	}
}
