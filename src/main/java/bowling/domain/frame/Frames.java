package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

import bowling.domain.Constants;

public class Frames {

	private final List<Frame> frames = new ArrayList<>();

	public Frames() {}

	public void upsert(Frame frame) {
		if (!this.contains(frame)) {
			frames.add(frame);
		}
	}

	public int size() {
		return frames.size();
	}

	public boolean contains(Frame frame) {
		return this.getFrames().contains(frame);
	}

	public List<Frame> getFrames() {
		return frames;
	}

	public Boolean isUpsertNormalFrame(Frame frame) {
		return frames.contains(frame) || frames.size() + 1 < Constants.FINAL_FRAME;
	}

	public Boolean isNormalFrame(Frame frame) {
		return frames.indexOf(frame) + 1 < Constants.FINAL_FRAME;
	}

	public Boolean isFinalFrame(Frame frame) {
		return frames.indexOf(frame) + 1 == Constants.FINAL_FRAME;
	}

	public Boolean isUpsertFinalFrame(Frame frame) {
		return frames.contains(frame) || frames.size() + 1 == Constants.FINAL_FRAME;
	}
}
