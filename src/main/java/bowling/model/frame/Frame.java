package bowling.model.frame;

import java.util.Objects;

import bowling.model.play.FinalPlay;
import bowling.model.play.NormalPlay;
import bowling.model.play.Playable;
import bowling.model.score.Score;
import bowling.model.score.ScoreGenerator;

public abstract class Frame {

	private static final String EMPTY_VALUE = "";
	private static final int MIN_PLAY_COUNT = 1;
	private static final int MAX_PLAY_COUNT = 2;
	private static final int BONUS_PLAY_COUNT = 3;
	private static final int FIRST_INDEX = 0;
	private static final int SECOND_INDEX = 1;
	private static final int BONUS_INDEX = 2;

	protected final Playable play;
	protected final int frameNumber;

	public Frame(int frameNumber) {
		checkFrameNumber(frameNumber);
		this.play = initPlayable(frameNumber);
		this.frameNumber = frameNumber;
	}

	private Playable initPlayable(int frameNumber) {
		if (frameNumber == 10) {
			return new FinalPlay(frameNumber);
		}
		return new NormalPlay(frameNumber);
	}

	public String getGameStatus() {
		if (play.countGame() == MIN_PLAY_COUNT) {
			return ScoreGenerator.scoreGenerator(play.findPin(FIRST_INDEX));
		}
		if (play.countGame() == MAX_PLAY_COUNT) {
			return ScoreGenerator.scoreGenerator(play.findPin(FIRST_INDEX), play.findPin(SECOND_INDEX));
		}
		if (play.countGame() == BONUS_PLAY_COUNT) {
			return ScoreGenerator.scoreGenerator(play.findPin(FIRST_INDEX), play.findPin(SECOND_INDEX),
				play.findPin(BONUS_INDEX));
		}
		return EMPTY_VALUE;
	}

	public int getFrameNumber() {
		return frameNumber;
	}

	abstract void playGame(int strikeNumber);

	abstract void checkFrameNumber(int frameNumber);

	public abstract boolean isGameEnd();

	abstract int calculateScore(Score score);

	public abstract int getGameScore();

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Frame frame = (Frame)o;
		return frameNumber == frame.frameNumber && Objects.equals(play, frame.play);
	}

	@Override
	public int hashCode() {
		return Objects.hash(play, frameNumber);
	}
}
