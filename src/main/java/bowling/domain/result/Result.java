package bowling.domain.result;

import java.util.Arrays;
import java.util.List;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public enum Result {
	STRIKE {
		@Override
		boolean isMatch(Score first, Score second) {
			return first.isScoreTen() && second.isScoreZero();
		}

		@Override
		public Score calculateFrameTotalScore(Score currentFrameScore, Scores nextFrameScores) {
			return currentFrameScore.add(nextFrameScores.calculateFrameTotalScore());
		}
	},
	SPARE {
		@Override
		boolean isMatch(Score first, Score second) {
			return ! first.isScoreTen() && first.add(second).isScoreTen();
		}

		@Override
		public Score calculateFrameTotalScore(Score currentFrameScore, Scores nextFrameScores) {
			return nextFrameScores.getFirst()
				.map(score -> score.add(currentFrameScore))
				.orElse(currentFrameScore);
		}
	},
	MISS {
		@Override
		boolean isMatch(Score first, Score second) {
			return ! first.isScoreTen() && first.add(second).isBetweenInRange();
		}

		@Override
		public Score calculateFrameTotalScore(Score currentFrameScore, Scores nextFrameScores) {
			return currentFrameScore;
		}
	},
	GUTTER {
		@Override
		boolean isMatch(Score first, Score second) {
			return first.isScoreZero() && first.add(second).isScoreZero();
		}

		@Override
		public Score calculateFrameTotalScore(Score currentFrameScore, Scores nextFrameScores) {
			return currentFrameScore;
		}
	};

	public static Result findByScores(Score first, Score second) {
		return Arrays.stream(values())
			.filter(result -> result.isMatch(first, second))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("not in score expected possibilities."));
	}

	abstract boolean isMatch(Score first, Score second);

	public abstract Score calculateFrameTotalScore(Score currentFrameScore, Scores nextFrameScores);

	public static final List<Result> BONUS_SCORE_RESULT = Arrays.asList(Result.STRIKE, Result.SPARE);
}
