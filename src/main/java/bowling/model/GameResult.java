package bowling.model;

import java.util.List;
import java.util.Objects;

public class GameResult {

	private static final double MAX_PIN = 10;
	private static final int FIRST_INDEX = 0;
	private static final int SECOND_INDEX = 1;
	private static final int BONUS_INDEX = 2;

	private final List<Pin> gameResult;

	public GameResult(List<Pin> gameResult) {
		this.gameResult = gameResult;
	}

	public void save(Pin pin) {
		gameResult.add(pin);
	}

	public Pin findScore(int index) {
		return gameResult.get(index);
	}

	public boolean isStrikeOrSpare() {
		if (gameResult.size() == FIRST_INDEX) {
			return true;
		}
		if (gameResult.size() == SECOND_INDEX) {
			return findScore(FIRST_INDEX).getPin() == MAX_PIN;
		}
		if (gameResult.size() == BONUS_INDEX) {
			return (findScore(FIRST_INDEX).getPin() + findScore(SECOND_INDEX).getPin()) == MAX_PIN;
		}
		return false;
	}

	public int findTotalScore() {
		return gameResult.stream()
			.mapToInt(Pin::getPin)
			.sum();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		GameResult that = (GameResult)o;
		return Objects.equals(gameResult, that.gameResult);
	}

	@Override
	public int hashCode() {
		return Objects.hash(gameResult);
	}

}
