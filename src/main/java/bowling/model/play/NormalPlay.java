package bowling.model.play;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import bowling.model.Pin;

public class NormalPlay implements Playable {

	private static final String LIMIT_MAX_PIN_ERROR_MESSAGE = "최대 쓰러뜨릴 수 있는 수가 초과 하였습니다.";
	private static final int MAX_PLAY_NUMBER = 2;
	private static final int MAX_PIN = 10;
	private static final int FIRST_INDEX = 0;
	private static final int SECOND_INDEX = 1;
	private static final int MAX_PLAY_COUNT = 1;
	private static final int ZERO_POINT = 0;

	private int playCount;
	private final List<Pin> gameResult;

	public NormalPlay() {
		this.gameResult = new ArrayList<>(MAX_PLAY_NUMBER);
	}

	@Override
	public List<Pin> play(Pin pin) {
		checkScore(pin);
		playCount++;
		gameResult.add(pin);
		return Collections.unmodifiableList(gameResult);
	}

	public int findTotalScore() {
		return gameResult.stream()
			.mapToInt(Pin::getPin)
			.sum();
	}

	private void checkScore(Pin pin) {
		if ((findTotalScore() + pin.getPin()) > MAX_PIN) {
			throw new IllegalArgumentException(LIMIT_MAX_PIN_ERROR_MESSAGE);
		}
	}

	@Override
	public boolean isFirstStrike() {
		return gameResult.get(FIRST_INDEX).getPin() == MAX_PIN;
	}

	@Override
	public boolean isNotStrikeOrSpare() {
		if (gameResult.get(FIRST_INDEX).getPin() < MAX_PIN) {
			return true;
		}
		return (gameResult.get(FIRST_INDEX).getPin() + gameResult.get(SECOND_INDEX).getPin()) < MAX_PIN;
	}

	@Override
	public boolean isGameEnd() {
		if (gameResult.size() == ZERO_POINT) {
			return false;
		}
		return (playCount > ZERO_POINT && isFirstStrike()) || playCount > MAX_PLAY_COUNT;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		NormalPlay that = (NormalPlay)o;
		return playCount == that.playCount && Objects.equals(gameResult, that.gameResult);
	}

	@Override
	public int hashCode() {
		return Objects.hash(playCount, gameResult);
	}
}
