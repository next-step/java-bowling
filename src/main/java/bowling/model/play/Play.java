package bowling.model.play;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import bowling.model.Pin;

public class Play implements Playable {
	private static final String LIMIT_MAX_PIN_ERROR_MESSAGE = "최대 쓰러뜨릴 수 있는 수가 초과 하였습니다.";
	private static final int MAX_PIN = 10;
	private static final int MIN_PLAY_COUNT = 1;
	private static final int MAX_PLAY_COUNT = 2;
	private static final int FIRST_INDEX = 0;
	private static final int SECOND_INDEX = 1;
	private static final int FINAL_FRAME_NUMBER = 10;

	private final int frameNumber;
	private final List<Pin> gameResult;

	public Play(int frameNumber) {
		this.frameNumber = frameNumber;
		this.gameResult = new ArrayList<>();
	}

	@Override
	public Play play(Pin pin) {
		checkScore(pin);
		gameResult.add(pin);
		return this;
	}

	public List<Pin> getGameResult() {
		return Collections.unmodifiableList(gameResult);
	}

	@Override
	public int countGame() {
		return gameResult.size();
	}

	@Override
	public Pin findPin(int index) {
		return gameResult.get(index);
	}

	private void checkScore(Pin pin) {
		if (!isStrike() && !isSpare() && (countGame() > 0 && findPin(FIRST_INDEX).isOverPin(pin))) {
			throw new IllegalArgumentException(LIMIT_MAX_PIN_ERROR_MESSAGE);
		}
	}

	@Override
	public boolean isStrike() {
		if (countGame() == 0) {
			return false;
		}
		return gameResult.get(0).isMaxPin();
	}

	@Override
	public boolean isSpare() {
		if (isStrike()) {
			return false;
		}
		if (countGame() < MAX_PLAY_COUNT) {
			return false;
		}
		return findPin(FIRST_INDEX).add(findPin(SECOND_INDEX)).isMaxPin();
	}

	@Override
	public boolean isMiss() {
		if (isStrike()) {
			return false;
		}
		if (countGame() < MAX_PLAY_COUNT) {
			return false;
		}
		return !findPin(FIRST_INDEX).add(findPin(SECOND_INDEX)).isMaxPin();
	}

	@Override
	public boolean isGameEnd() {
		if ((isMiss() && (countGame() > MIN_PLAY_COUNT)) || countGame() > MAX_PLAY_COUNT) {
			return true;
		}
		return (frameNumber < FINAL_FRAME_NUMBER && (isStrike() || countGame() > MIN_PLAY_COUNT));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Play play = (Play)o;
		return frameNumber == play.frameNumber && Objects.equals(gameResult, play.gameResult);
	}

	@Override
	public int hashCode() {
		return Objects.hash(frameNumber, gameResult);
	}
}
