package domain.phase;

import domain.FrameResult;

public enum Phase {

	FIRST_PHASE {
		public FrameResult getFrameResult(int remainBowlingPins) {
			if (remainBowlingPins == 0) {
				return FrameResult.STRIKE;
			}
			return FrameResult.UNKNOWN;
		}
	},
	SECOND_PHASE {
		public FrameResult getFrameResult(int remainBowlingPins) {
			if (remainBowlingPins == 0) {
				return FrameResult.SPARE;
			}

			if (remainBowlingPins == 10) {
				return FrameResult.GUTTER;
			}

			return FrameResult.MISS;
		}
	};

	abstract FrameResult getFrameResult(int remainBowlingPins);

}
