package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores {

	private final List<Score> scores;

	private Scores(List<Score> scores) {
		this.scores = new ArrayList<>(scores);
	}

	public static Scores from(Frames frames) {
		List<Score> scores = new ArrayList<>();
		Score base = Score.ofZero();
		for (int frameNumber = Frame.MIN_NORMAL_FRAME_NUMBER; frameNumber <= Frame.LAST_FRAME_NUMBER; frameNumber++) {
			Score score = scoreOfFrame(frames, frames.of(frameNumber));
			base = base.add(score);
			scores.add(base);
		}
		return new Scores(scores);
	}

	public Score scoreOf(int frameNumber) {
		return scores.get(frameNumber - 1);
	}

	private static Score scoreOfFrame(Frames frames, Frame frame) {
		if (!frame.isEnd()) {
			return Score.ofUnscored();
		}

		Score score = frame.score();
		if (frame.isLast()) {
			return score;
		}
		return score.add(bonusScore(frames, frame));
	}

	private static Score bonusScore(Frames frames, Frame frame) {
		if (frame.hasStrike()) {
			return strikeBonus(frames, frame);
		}

		if (frame.hasSpare()) {
			return spareBonus(frames, frame);
		}
		return Score.ofZero();
	}

	private static Score strikeBonus(Frames frames, Frame frame) {
		Frame nextFrame = frames.of(frame.number() + 1);
		PitchResult next1stResult = nextFrame.firstResult();
		PitchResult next2ndResult = nextFrame.secondResult();
		Score next1stScore = Score.of(next1stResult);
		if (next1stResult.isStrike() && frame.number() < Frame.MAX_NORMAL_FRAME_NUMBER) {
			Frame nextAfterFrame = frames.of(frame.number() + 2);
			PitchResult nextAfter1stResult = nextAfterFrame.firstResult();
			return next1stScore.add(Score.of(nextAfter1stResult));
		}
		return next1stScore.add(Score.of(next2ndResult));
	}

	private static Score spareBonus(Frames frames, Frame target) {
		Frame nextFrame = frames.of(target.number() + 1);
		PitchResult nextResult = nextFrame.firstResult();
		return Score.of(nextResult);
	}

	public List<Score> scores() {
		return Collections.unmodifiableList(scores);
	}
}
