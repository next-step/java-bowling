package bowling.domain.result;

import java.util.Arrays;

import bowling.domain.score.Score;

public enum Result {
	STRIKE {
		@Override
		boolean isMatch(Score first, Score second) {
			return first.isScoreTen() && second.isScoreZero();
		}
	},
	SPARE {
		@Override
		boolean isMatch(Score first, Score second) {
			return ! first.isScoreTen() && first.add(second).isScoreTen();
		}
	},
	MISS {
		@Override
		boolean isMatch(Score first, Score second) {
			return ! first.isScoreTen() && ! first.add(second).isScoreTen() && ! first.add(second).isScoreZero();
		}
	},
	GUTTER {
		@Override
		boolean isMatch(Score first, Score second) {
			return first.isScoreZero() && first.add(second).isScoreZero();
		}
	};

	public static Result findByScores(Score first, Score second) {
		return Arrays.stream(values())
			.filter(result -> result.isMatch(first, second))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("not in score expected possibilities."));
	}

	abstract boolean isMatch(Score first, Score second);
}
