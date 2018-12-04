package domain;

import domain.frame.FrameResults;

import java.util.List;

/**
 * Created by hspark on 04/12/2018.
 */
public class BowlingScoreBoard {
	private Player player;
	private FrameResults frameResults;

	public BowlingScoreBoard(Player player, FrameResults frameResults) {
		this.player = player;
		this.frameResults = frameResults;
	}

	public Player getPlayer() {
		return player;
	}

	public List<String> getResultList() {
		return frameResults.toResultList();
	}

	public List<Integer> getScoreList() {
		return frameResults.toScoreList();
	}
}
