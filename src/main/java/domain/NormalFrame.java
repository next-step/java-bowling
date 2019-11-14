package domain;

import domain.phase.NormalFramePhaseCollection;
import domain.phase.PhaseCollection;

public class NormalFrame {

	private static final int START_BOWLING_PINS = 10;

	private PhaseCollection phaseCollection;
	private int remainBowlingPins;

	public NormalFrame() {
		this.phaseCollection = new NormalFramePhaseCollection();
		this.remainBowlingPins = START_BOWLING_PINS;
	}

	public FrameResult shoot(int fallenBowlingPins) {
		if (remainBowlingPins < fallenBowlingPins) {
			throw new IllegalStateException("서 있는 볼링핀보다 더 많은 볼링핀을 쓰러뜨릴 수는 없겠죠?");
		}

		remainBowlingPins -= fallenBowlingPins;
		return phaseCollection.getFrameResult(remainBowlingPins);
	}

}
