package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hspark on 05/12/2018.
 */
public class BowlingGames {
	private int currentIndex;
	private List<Bowling> bowlingGames;

	public BowlingGames(String[] players) {
		this.currentIndex = 0;
		this.bowlingGames = Arrays.stream(players).map(Player::new).map(Bowling::new).collect(Collectors.toList());
	}

	public List<Bowling> getBowlingGames() {
		return bowlingGames;
	}

	public BowlingScoreBoard bowl(Pin pin) {
		bowlingGames.get(currentIndex).bowl(pin);
		return bowlingGames.get(currentIndex).getBowlingScoreBoard();
	}

	public Bowling getCurrentGame() {
		Bowling currentBowling = bowlingGames.get(currentIndex);

		if (isAllFinished()) {
			return currentBowling;
		}

		BowlingScoreBoard currentScoreBoard = currentBowling.getBowlingScoreBoard();
		if (currentScoreBoard.isRecentFrameFinished() || !currentBowling.hasNext()) {
			return bowlingGames.get(getNextIndex());
		}
		return currentBowling;
	}

	private int getNextIndex() {
		currentIndex = (++currentIndex) % bowlingGames.size();
		return currentIndex;
	}

	public boolean isAllFinished() {
		return bowlingGames.stream().allMatch(bowling -> !bowling.hasNext());
	}
}
