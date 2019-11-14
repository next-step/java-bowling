package domain.phase;

import domain.PhaseResult;

public enum Phase {

	FIRST_PHASE {
		@Override
		public PhaseResult getFrameResult(int remainBowlingPins) {
			if (remainBowlingPins == 0) {
				return PhaseResult.STRIKE;
			}
			return PhaseResult.IN_PROGRESS;
		}
	},
	SECOND_PHASE {
		@Override
		public PhaseResult getFrameResult(int remainBowlingPins) {
			if (remainBowlingPins == 0) {
				return PhaseResult.SPARE;
			}

			if (remainBowlingPins == 10) {
				return PhaseResult.GUTTER;
			}

			return PhaseResult.MISS;
		}
	},
	THIRD_PHASE {
		@Override
		PhaseResult getFrameResult(int remainBowlingPins) {
			return PhaseResult.LAST_SCORE;
		}
	};

	abstract PhaseResult getFrameResult(int remainBowlingPins);

}
