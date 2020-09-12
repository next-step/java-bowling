package bowling.pitching;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;

import bowling.exception.PitchingStateException;
import bowling.pin.Pins;

public enum PitchingState {
	READY(0, Strings.EMPTY) {
		@Override
		public PitchingState reflect(List<Pins> knockingDownPins) {
			return knockingDownPins.get(0).isAllKnockingDown() ? STRIKE : ON_GOING;
		}

		@Override
		public boolean canMoveNextFrame() {
			return false;
		}
	},

	STRIKE(2, "X") {
		@Override
		public PitchingState reflect(List<Pins> knockingDownPins) {
			if (knockingDownPins.get(knockingDownPins.size() - 1).isAllKnockingDown()) {
				return PitchingState.STRIKE;
			}
			return PitchingState.ON_GOING;
		}

		@Override
		public boolean canMoveNextFrame() {
			return true;
		}
	},

	ON_GOING(0, Strings.EMPTY) {
		@Override
		public PitchingState reflect(List<Pins> knockingDownPins) {
			Pins firstKnockingDownPins = knockingDownPins.get(0);
			Pins secondKnockingDownPins = knockingDownPins.get(1);
			if (secondKnockingDownPins.isNotKnockedDownAtAll()) {
				return GUTTER;
			}

			int totalKnockingDownPins = firstKnockingDownPins.addKnockingDownPins(secondKnockingDownPins);
			return totalKnockingDownPins == ALL_PIN_COUNT ? SPARE : MISS;
		}

		@Override
		public boolean canMoveNextFrame() {
			return false;
		}
	},

	SPARE(1, "/") {
		@Override
		public PitchingState reflect(List<Pins> knockingDownPins) {
			if (knockingDownPins.get(knockingDownPins.size() - 1).isAllKnockingDown()) {
				return PitchingState.STRIKE;
			}
			return PitchingState.ON_GOING;
		}

		@Override
		public boolean canMoveNextFrame() {
			return true;
		}
	},

	MISS(0, Strings.EMPTY) {
		@Override
		public PitchingState reflect(List<Pins> knockingDownPins) {
			throw new PitchingStateException("해당 프레임에서는 더 던질 수 없습니다.");
		}

		@Override
		public boolean canMoveNextFrame() {
			return true;
		}
	},

	GUTTER(0, "-") {
		@Override
		public PitchingState reflect(List<Pins> knockingDownPins) {
			throw new PitchingStateException("해당 프레임에서는 더 던질 수 없습니다.");
		}

		@Override
		public boolean canMoveNextFrame() {
			return true;
		}
	};

	private static final int ALL_PIN_COUNT = 10;

	public abstract PitchingState reflect(List<Pins> knockingDownPins);

	public abstract boolean canMoveNextFrame();

	private final int nextPitchCountToReflect;
	private final String sign;

	public int getNextPitchingCountToReflect(int currentFrameNo) {
		return currentFrameNo == 10 ? 0 : nextPitchCountToReflect;
	}

	public String getKnockingDownPinsSign(Pins totalKnockingDownPinsToDate) {
		return !StringUtils.isEmpty(sign) ? sign : String.valueOf(totalKnockingDownPinsToDate.getKnockingDownPins());
	}

	public boolean isStrike() {
		return this == STRIKE;
	}

	public boolean isSpare() {
		return this == SPARE;
	}

	public boolean isStrikeOrSpare() {
		return isStrike() || isSpare();
	}

	PitchingState(int nextPitchCountToReflect, String sign) {
		this.nextPitchCountToReflect = nextPitchCountToReflect;
		this.sign = sign;
	}
}
