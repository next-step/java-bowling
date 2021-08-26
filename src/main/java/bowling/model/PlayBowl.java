package bowling.model;

import java.util.ArrayList;
import java.util.Objects;

public class PlayBowl {

	private static final String LIMIT_MAX_PIN_ERROR_MESSAGE = "최대 쓰러뜨릴 수 있는 수가 초과 하였습니다.";
	private static final int MAX_RESULT_SIZE = 3;
	private static final int MAX_ROUND = 10;
	private static final int MAX_PIN = 10;

	private final int frameNumber;
	private final PlayResult playResult;

	public PlayBowl(int frameNumber) {
		this.frameNumber = frameNumber;
		this.playResult = new PlayResult(new ArrayList<>(MAX_RESULT_SIZE));
	}

	public PlayResult play(Pin pin) {
		if (frameNumber < MAX_ROUND) {
			checkNormalScore(pin);
		}
		if (frameNumber == MAX_ROUND) {
			checkFinalScore(pin);
		}
		playResult.save(pin);
		return playResult;
	}

	public PlayResult play(int pin) {
		Pin playPin = new Pin(pin);
		if (frameNumber < MAX_ROUND) {
			checkNormalScore(playPin);
		}
		if (frameNumber == MAX_ROUND) {
			checkFinalScore(playPin);
		}
		playResult.save(playPin);
		return playResult;
	}

	private void checkNormalScore(Pin pin) {
		if ((playResult.findTotalScore() + pin.getPin()) > MAX_PIN) {
			throw new IllegalArgumentException(LIMIT_MAX_PIN_ERROR_MESSAGE);
		}
	}

	private void checkFinalScore(Pin pin) {
		if (playResult.isStrikeOrSpare() && pin.getPin() > MAX_PIN) {
			throw new IllegalArgumentException(LIMIT_MAX_PIN_ERROR_MESSAGE);
		}

		if ((!playResult.isStrikeOrSpare() && (playResult.findReminderScore() + pin.getPin()) > MAX_PIN)) {
			throw new IllegalArgumentException(LIMIT_MAX_PIN_ERROR_MESSAGE);
		}
	}

	public boolean isStrike() {
		return playResult.isFirstStrike();
	}

	public boolean isNotStrikeOrSpare() {
		return playResult.isNotStrikeOrSpare();
	}

	public String getGameScore() {
		return playResult.getGameScore();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PlayBowl playBowl = (PlayBowl)o;
		return frameNumber == playBowl.frameNumber && Objects.equals(playResult, playBowl.playResult);
	}

	@Override
	public int hashCode() {
		return Objects.hash(frameNumber, playResult);
	}

}
