package domain.phase;

import domain.phase.result.FinalPhaseResult;
import domain.phase.result.PhaseResult;

public enum FinalPhase implements Phase {

	FIRST_PHASE {
		@Override
		public PhaseResult getFrameResult(int remainBowlingPins) {
			if (remainBowlingPins == 0) {
				return FinalPhaseResult.STRIKE;
			}
			return FinalPhaseResult.IN_PROGRESS;
		}
	},
	SECOND_PHASE {
		@Override
		public PhaseResult getFrameResult(int remainBowlingPins) {
			if (remainBowlingPins == 0) {
				return FinalPhaseResult.SPARE;
			}

			if (remainBowlingPins == 10) {
				return FinalPhaseResult.GUTTER;
			}

			return FinalPhaseResult.MISS;
		}
	},
	THIRD_PHASE {
		@Override
		public PhaseResult getFrameResult(int remainBowlingPins) {
			return FinalPhaseResult.LAST_SCORE;
		}
	};

	public abstract PhaseResult getFrameResult(int remainBowlingPins);

}
