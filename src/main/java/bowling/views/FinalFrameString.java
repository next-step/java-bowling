package bowling.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bowling.entity.BowlingScoreType;
import bowling.entity.Ward;

public enum FinalFrameString {

	ZERO(0) {
		@Override
		public String stringMethod(List<Ward> wards) {
			return "";
		}
	},

	ONE(1) {
		@Override
		public String stringMethod(List<Ward> wards) {
			BowlingScoreType type = BowlingScoreType.getInstance(wards.get(0), null);
			return BowlingScoreString.getScoreString(type, wards.get(0), null);
		}
	},

	TWO(2) {
		@Override
		public String stringMethod(List<Ward> wards) {
			String result = ONE.stringMethod(new ArrayList<>(wards.subList(0, 1)));
			if (BowlingScoreType.getInstance(wards.get(0), null) == BowlingScoreType.STRIKE) {
				return result + "|" + BowlingScoreString.getScoreString(BowlingScoreType.getInstance(wards.get(1), null), wards.get(1), null);
			}
			return BowlingScoreString.getScoreString(BowlingScoreType.getInstance(wards.get(0), wards.get(1)), wards.get(0), wards.get(1));
		}
	},

	THREE(3) {
		@Override
		public String stringMethod(List<Ward> wards) {
			String result = TWO.stringMethod(new ArrayList<>(wards.subList(0, 2)));
			if (BowlingScoreType.getInstance(wards.get(1), null) == BowlingScoreType.STRIKE
				|| (!ONE.stringMethod(wards).trim().equals("X") && wards.get(0).getScore() + wards.get(1).getScore() == 10)
			) {
				return result + "|" + BowlingScoreString.getScoreString(BowlingScoreType.getInstance(wards.get(2), null), wards.get(2), null);
			}
			return result + "|" + BowlingScoreString.getScoreString(BowlingScoreType.getInstance(wards.get(1), wards.get(2)), wards.get(1), wards.get(2));
		}
	};

	private final int size;

	FinalFrameString(int size) {
		this.size = size;
	}

	public abstract String stringMethod(List<Ward> wards);

	public static String getString(List<Ward> wards) {
		return Arrays.stream(values())
			.filter(frameString -> frameString.size == wards.size())
			.findFirst()
			.get()
			.stringMethod(wards);
	}
}
