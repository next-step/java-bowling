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

	private final List<Pin> gameResult;

	public PlayResult(List<Pin> gameResult) {
		this.gameResult = Collections.unmodifiableList(gameResult);
	}

	public Pin findScore(int index) {
		return gameResult.get(index);
	}

	public int findTotalScore() {
		return gameResult.stream()
			.mapToInt(Pin::getPin)
			.sum();
	}

	public String getGameScore() {
		if (gameResult.size() == FIRST_INDEX_SIZE) {
			return ScoreGenerator.scoreGenerator(findScore(FIRST_INDEX).getPin());
		}
		if (gameResult.size() == SECOND_INDEX_SIZE) {
			return ScoreGenerator.scoreGenerator(findScore(FIRST_INDEX).getPin(), findScore(SECOND_INDEX).getPin());
		}
		if (gameResult.size() == BONUS_INDEX_SIZE) {
			return ScoreGenerator.scoreGenerator(findScore(FIRST_INDEX).getPin(), findScore(SECOND_INDEX).getPin(),
				findScore(BONUS_INDEX).getPin());
		}
		return EMPTY_VALUE;
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
