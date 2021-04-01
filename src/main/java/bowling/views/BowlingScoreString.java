package bowling.views;

import java.util.Arrays;

import bowling.entity.BowlingScoreType;
import bowling.entity.Ward;

public enum BowlingScoreString {
	STRIKE(BowlingScoreType.STRIKE, "X") {
		@Override
		public String scoreToString(Ward first, Ward second) {
			return STRIKE.scoreString;
		}
	},

	SPARE(BowlingScoreType.SPARE, "/") {
		@Override
		public String scoreToString(Ward first, Ward second) {
			return first.getScore() + "|" + SPARE.scoreString;
		}
	},

	MISS(BowlingScoreType.MISS, "-") {
		@Override
		public String scoreToString(Ward first, Ward second) {
			return first.getScore() + "|" + MISS.scoreString;
		}
	},

	GUTTER(BowlingScoreType.GUTTER, "-") {
		@Override
		public String scoreToString(Ward first, Ward second) {
			return GUTTER.scoreString;
		}
	},

	OTHER(BowlingScoreType.OTHER, "") {
		@Override
		public String scoreToString(Ward first, Ward second) {
			if (second == null) {
				return String.valueOf(first.getScore());
			}
			return first.getScore() + "|" + second.getScore();
		}
	};

	private final BowlingScoreType type;
	private final String scoreString;

	BowlingScoreString(BowlingScoreType type, String scoreString) {
		this.type = type;
		this.scoreString = scoreString;
	}

	public abstract String scoreToString(Ward first, Ward second);

	BowlingScoreType getType() {
		return this.type;
	}

	public String getScoreString() {
		return this.scoreString;
	}

	public static String getScoreString(BowlingScoreType type, Ward firstWard, Ward secondWard) {
		String score = Arrays.stream(values())
			.filter(bowlingScoreString -> bowlingScoreString.type == type)
			.findFirst()
			.orElseGet(() -> OTHER)
			.scoreToString(firstWard, secondWard);
		return String.format("%3s", score);
	}
}
