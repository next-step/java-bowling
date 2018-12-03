package domain;

import domain.frame.result.FrameResults;

import java.util.List;

/**
 * Created by hspark on 22/11/2018.
 */
public class BowlingScoreBoard {
	private FrameResults frameResults;
	private FrameScores frameScores;

	public BowlingScoreBoard(FrameResults frameResults, FrameScores frameScores) {
		this.frameResults = frameResults;
		this.frameScores = frameScores;
	}

	public List<String> gameResult() {
		return frameResults.toList();
	}

	public List<Integer> scores() {
		return frameScores.toList();
	}

}
