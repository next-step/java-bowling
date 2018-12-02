package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hspark on 02/12/2018.
 */
public class FrameScores {
	public static final int NON_CALCULATE_SCORE = -1;
	private List<Integer> sumScores;

	public FrameScores(List<Integer> scores) {
		scores = filterUnCalculateScore(scores);
		this.sumScores = initScores(scores);
	}

	private List<Integer> initScores(List<Integer> scores) {
		List<Integer> sumScores = new ArrayList<>();
		int totalScore = 0;
		for (Integer score : scores) {
			totalScore += score;
			sumScores.add(totalScore);
		}
		return sumScores;
	}

	private List<Integer> filterUnCalculateScore(List<Integer> scores) {
		return scores.stream().filter(integer -> integer != NON_CALCULATE_SCORE)
			.collect(Collectors.toList());
	}

	public int getTotalScoreToFrameNumber(int frameNumber) {
		return sumScores.get(frameNumber - 1);
	}

	public List<Integer> toList() {
		return sumScores;
	}
}
