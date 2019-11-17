package domain.phase;

import domain.phase.result.NormalPhaseResult;
import domain.phase.result.PhaseResult;

public enum NormalPhase implements Phase {

	FIRST_PHASE {
		@Override
		public PhaseResult getFrameResult(int remainBowlingPins) {
			if (remainBowlingPins == 0) {
				return NormalPhaseResult.STRIKE;
			}
			return NormalPhaseResult.IN_PROGRESS;
		}
	},
	SECOND_PHASE {
		@Override
		public PhaseResult getFrameResult(int remainBowlingPins) {
			if (remainBowlingPins == 0) {
				return NormalPhaseResult.SPARE;
			}

			if (remainBowlingPins == 10) {
				return NormalPhaseResult.GUTTER;
			}

			return NormalPhaseResult.MISS;
		}
	};

	public abstract PhaseResult getFrameResult(int remainBowlingPins);

}
