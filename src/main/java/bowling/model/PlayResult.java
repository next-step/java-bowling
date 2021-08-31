package bowling.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PlayResult {

	private static final String EMPTY_VALUE = "";
	private static final int FIRST_INDEX = 0;
	private static final int SECOND_INDEX = 1;
	private static final int BONUS_INDEX = 2;
	private static final int FIRST_INDEX_SIZE = 1;
	private static final int SECOND_INDEX_SIZE = 2;
	private static final int BONUS_INDEX_SIZE = 3;
	private static final int MAX_PIN = 10;

	private final List<Pin> gameResult;

	public PlayResult(List<Pin> gameResult) {
		this.gameResult = Collections.unmodifiableList(gameResult);
	}

	public int findScore(int index) {
		return gameResult.get(index).getPin();
	}

	public String getGameResult() {
		if (gameResult.size() == FIRST_INDEX_SIZE) {
			return ScoreGenerator.scoreGenerator(findScore(FIRST_INDEX));
		}
		if (gameResult.size() == SECOND_INDEX_SIZE) {
			return ScoreGenerator.scoreGenerator(findScore(FIRST_INDEX), findScore(SECOND_INDEX));
		}
		if (gameResult.size() == BONUS_INDEX_SIZE) {
			return ScoreGenerator.scoreGenerator(findScore(FIRST_INDEX), findScore(SECOND_INDEX),
				findScore(BONUS_INDEX));
		}
		return EMPTY_VALUE;
	}

	public boolean isGameStart() {
		return gameResult.size() > FIRST_INDEX;
	}

	public int countGame() {
		return gameResult.size();
	}

	public int calculateFrame(PlayResult beforeResult) {
		if (beforeResult.isSpare() && isGameStart()) {
			return MAX_PIN + findScore(FIRST_INDEX);
		}
		if (beforeResult.isStrike() && countGame() > 1) {
			return MAX_PIN + (findScore(FIRST_INDEX) + findScore(SECOND_INDEX));
		}
		return -1;
	}

	public int calculateDouble(PlayResult beforeResult) {
		if (isGameStart()) {
			return (MAX_PIN + beforeResult.getTotalScore()) + findScore(FIRST_INDEX);
		}
		return -1;
	}

	public int getTotalScore() {
		return gameResult.stream()
			.mapToInt(Pin::getPin)
			.sum();
	}

	public boolean isMaxPin() {
		return gameResult.get(FIRST_INDEX).isMaxPin();
	}

	public boolean isMaxPin(Pin secondPin) {
		return gameResult.get(FIRST_INDEX).add(secondPin).isMaxPin();
	}

	public boolean isStrike() {
		return gameResult.size() > FIRST_INDEX && (isMaxPin());
	}

	public boolean isSpare() {
		if (isStrike()) {
			return false;
		}
		if (gameResult.size() < SECOND_INDEX_SIZE) {
			return false;
		}
		return isMaxPin(gameResult.get(SECOND_INDEX));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PlayResult that = (PlayResult)o;
		return Objects.equals(gameResult, that.gameResult);
	}

	@Override
	public int hashCode() {
		return Objects.hash(gameResult);
	}

}
