package domain;

import domain.frame.result.FrameResult;

import java.util.*;

/**
 * Created by hspark on 22/11/2018.
 */
public class BowlingScoreBoard {
	private Map<Integer, String> board = new HashMap<>();

	public BowlingScoreBoard(List<FrameResult> frameResults) {
		for (FrameResult frameResult : frameResults) {
			String scoreStr = this.board.get(frameResult.getFrameNumber());
			if (Objects.nonNull(scoreStr)) {
				String newScoreStr = String.join("|", scoreStr, frameResult.toString());
				board.put(frameResult.getFrameNumber(), newScoreStr);
				continue;
			}
			board.put(frameResult.getFrameNumber(), frameResult.toString());
		}
	}

	public List<String> scores() {
		return Collections.unmodifiableList(new ArrayList<>(board.values()));
	}
}
