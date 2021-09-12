package bowling.domain.player;

import java.util.List;
import java.util.stream.Collectors;

import bowling.domain.common.Pins;
import bowling.domain.frame.Frames;
import bowling.domain.score.Score;
import bowling.domain.state.PitchStates;

public class Bowler {

	private final Player player;
	private final Frames frames;

	private Bowler(final String name) {
		player = Player.of(name);
		frames = Frames.of();
	}

	public static Bowler of(final String name) {
		return new Bowler(name);
	}

	public String getName() {
		return player.getName();
	}

	public boolean isFinish() {
		return frames.isFinish();
	}

	public List<PitchStates> getAllPitchStates() {
		return frames.getAllPitchStates();
	}

	public void pitch(final int pins) {
		frames.hitPins(Pins.of(pins));
	}

	public List<Score> getComputeAbleScores() {
		return frames.getScores().stream()
			.filter(Score::isComputeAble)
			.collect(Collectors.toList());
	}

	public boolean needBowlerChange() {
		return frames.currentFrame().isStart();
	}
}
