package domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hspark on 02/12/2018.
 */
public class FrameResults {

	private List<FrameResult> frameResults;

	public FrameResults(List<FrameResult> states) {
		this.frameResults = states;
	}

	public FrameResult getByFrameNumber(int frameNumber) {
		return frameResults.get(frameNumber - 1);
	}

	public int getFrameResultCount() {
		return frameResults.size();
	}

	public List<String> toResultList() {
		List<String> resultList = frameResults.stream().map(FrameResult::getResult).collect(Collectors.toList());
		return Collections.unmodifiableList(resultList);
	}

	public List<Integer> toScoreList() {
		List<Integer> scores = frameResults.stream().filter(FrameResult::isCalculatedScore)
			.map(FrameResult::getScore).collect(Collectors.toList());
		return createSummaryScores(scores);
	}

	private List<Integer> createSummaryScores(List<Integer> scores) {
		List<Integer> sumScores = new ArrayList<>();
		int totalScore = 0;
		for (Integer score : scores) {
			totalScore += score;
			sumScores.add(totalScore);
		}
		return sumScores;
	}

}
