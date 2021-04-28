package bowling.domain;

import bowling.exception.InvalidPlayCountException;

public abstract class Frame {
	protected final int frameNumber;
	private final Pins pins;
	protected int playCount = 0;

	public Frame(int frameNumber) {
		validateFrameNumber(frameNumber);
		this.frameNumber = frameNumber;
		this.pins = new Pins(frameNumber);
	}

	public void bowl(int hittingNumber) {
		if (isEndFrame()) {
			throw new InvalidPlayCountException("횟수를 초과했습니다.");
		}
		validatePlayCount();
		pins.play(hittingNumber);
		playCount++;
	}

	public PinStatus pinStatus() {
		return pins.pinStatus();
	}

	public int frameNumber() {
		return frameNumber;
	}

	public int playCount() {
		return playCount;
	}
	abstract void validateFrameNumber(int frameNumber);
	abstract void validatePlayCount();
	public abstract boolean isEndFrame();
}
