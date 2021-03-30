package bowling.entity;

import java.util.Arrays;

public enum BowlingScoreString {
	STRIKE(BowlingScoreType.STRIKE) {
		@Override
		public String scoreToString(Ward first, Ward second) {
			return this.getType().getScore();
		}
	},

	SPARE(BowlingScoreType.SPARE) {
		@Override
		public String scoreToString(Ward first, Ward second) {
			return first.getScore() + "|" + this.getType().getScore();
		}
	},

	MISS(BowlingScoreType.MISS) {
		@Override
		public String scoreToString(Ward first, Ward second) {
			return first.getScore() + "|" + this.getType().getScore();
		}
	},

	GUTTER(BowlingScoreType.GUTTER) {
		@Override
		public String scoreToString(Ward first, Ward second) {
			return this.getType().getScore();
		}
	},

	OTHER(BowlingScoreType.OTHER) {
		@Override
		public String scoreToString(Ward first, Ward second) {
			if (second == null) {
				return String.valueOf(first.getScore());
			}
			return first.getScore() + "|" + second.getScore();
		}
	};

	private final BowlingScoreType type;

	BowlingScoreString(BowlingScoreType type) {
		this.type = type;
	}

	public abstract String scoreToString(Ward first, Ward second);

	BowlingScoreType getType() {
		return this.type;
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
