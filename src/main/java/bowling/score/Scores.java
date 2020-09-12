package bowling.score;

import java.util.ArrayList;
import java.util.List;

public class Scores {
	private List<Score> scores;

	private Scores() {
		this.scores = new ArrayList<>();
	}

	public static Scores newInstance() {
		return new Scores();
	}

	public void calculateScore(Score score) {
		add(score);
		execute();
	}

	private void execute() {
		List<Score> removeScores = new ArrayList<>();
		addAdditionalNextScores();
		reflectPreviousScore(removeScores);
		removeUnnecessaryScores(removeScores);
	}

	private void addAdditionalNextScores() {
		for (int i = 0; i < scores.size(); i++) {
			this.addAdditionalNextScores(i);
		}
	}

	private void reflectPreviousScore(List<Score> removeScores) {
		for (int i = 0; i < scores.size(); i++) {
			this.reflectPreviousScore(i, removeScores);
		}
	}

	private void removeUnnecessaryScores(List<Score> removeScores) {
		for (Score removeScore : removeScores) {
			scores.remove(removeScore);
		}
	}

	private void add(Score score) {
		scores.add(score);
	}

	private void addAdditionalNextScores(int index) {
		if (!shouldAddAdditionalNextScores(index)) {
			return;
		}

		Score score = scores.get(index);
		int nextScoreIndex = index + 1;
		int currentPitchingCount = nextScoreIndex + score.getNextPitchingCountToReflect();

		for (int i = nextScoreIndex; i < currentPitchingCount; i++) {
			score.addAdditionalScore(scores.get(i));
		}
	}

	private boolean shouldAddAdditionalNextScores(int currentIndex) {
		Score currentScore = scores.get(currentIndex);
		int currentMaxIndex = scores.size() - 1;
		int advancedPitchCount = currentMaxIndex - currentIndex;

		return currentScore.isItTimeToReflectNextScores(advancedPitchCount);
	}

	private void reflectPreviousScore(int index, List<Score> removeScores) {
		if (index == 0) {
			return;
		}

		Score currentScore = scores.get(index);
		Score previousScore = scores.get(index - 1);
		boolean reflectPreviousScore = currentScore.reflectPreviousScore(previousScore);

		if (reflectPreviousScore && !previousScore.isLastPitchingOfCurrentFrame()) {
			removeScores.add(previousScore);
		}
	}

	public List<Score> getScores() {
		return scores;
	}
}
