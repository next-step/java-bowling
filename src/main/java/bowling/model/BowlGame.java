package bowling.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BowlGame {

	private static final String LIMIT_MAX_PIN_ERROR_MESSAGE = "최대 쓰러뜨릴 수 있는 수가 초과 하였습니다.";
	private static final int MAX_RESULT_SIZE = 3;
	private static final int MAX_ROUND = 10;
	private static final int MAX_PIN = 10;

	private final int frameNumber;
	private final List<Pin> gameResult;

	public BowlGame(int frameNumber) {
		this.frameNumber = frameNumber;
		this.gameResult = new ArrayList<>(MAX_RESULT_SIZE);
	}

	public List<Pin> play(Pin pin) {
		checkNormalScore(pin);
		checkFinalScore(pin);
		gameResult.add(pin);
		return gameResult;
	}

	private void checkNormalScore(Pin pin) {
		if (frameNumber < MAX_ROUND && (findTotalScore() + pin.getPin()) > MAX_PIN) {
			throw new IllegalArgumentException(LIMIT_MAX_PIN_ERROR_MESSAGE);
		}
	}

	private void checkFinalScore(Pin pin) {
		int totalScore = findTotalScore() + pin.getPin();
		if (frameNumber == MAX_ROUND && (isStrikeOrSpare() && pin.getPin() > MAX_PIN)) {
			throw new IllegalArgumentException(LIMIT_MAX_PIN_ERROR_MESSAGE);
		}
		if (frameNumber == MAX_ROUND && (!isStrikeOrSpare() && totalScore > MAX_PIN)) {
			throw new IllegalArgumentException(LIMIT_MAX_PIN_ERROR_MESSAGE);
		}
	}

	private boolean isStrikeOrSpare() {
		if (gameResult.size() == 0) {
			return true;
		}
		if (gameResult.size() == 1) {
			return gameResult.get(0).getPin() == MAX_PIN;
		}
		if (gameResult.size() == 2) {
			return (gameResult.get(0).getPin() + gameResult.get(1).getPin()) == MAX_PIN;
		}
		return false;
	}

	private int findTotalScore() {
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
		BowlGame bowlGame = (BowlGame)o;
		return frameNumber == bowlGame.frameNumber && Objects.equals(gameResult, bowlGame.gameResult);
	}

	@Override
	public int hashCode() {
		return Objects.hash(frameNumber, gameResult);
	}

}
