package bowling.domain.state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bowling.domain.score.Score;

public class PitchStates {

	private final List<PitchState> values;

	private PitchStates(final List<PitchState> values) {
		this.values = values;
	}

	public static PitchStates of(final List<PitchState> pitchStates) {
		return new PitchStates(pitchStates);
	}

	public static PitchStates of(final PitchState pitchState) {
		final ArrayList<PitchState> pitchStates = new ArrayList<>();
		pitchStates.add(pitchState);

		return new PitchStates(pitchStates);
	}

	public List<PitchState> subList(final int from, final int to) {
		return values.subList(from, to);
	}

	public void remove(final int index) {
		values.remove(index);
	}

	public void add(final PitchState pitchState) {
		values.add(pitchState);
	}

	public Score addScore(final Score score) {
		return values.stream()
			.reduce(score,
				(total, state) -> state.addScore(total),
				(x, y) -> {
					throw new IllegalStateException();
				});
	}

	public int size() {
		return values.size();
	}

	public boolean isLastAllHit() {
		return getLast().isAllHit();
	}

	public boolean isLastMiss() {
		return getLast().isMiss();
	}

	public PitchState getStart() {
		return values.get(0);
	}

	public Score getScore() {
		return values.subList(1, values.size()).stream()
			.reduce(getStart().score(),
				(total, state) -> state.addScore(total),
				(x, y) -> {
					throw new IllegalStateException();
				});
	}

	public PitchState getLast() {
		return values.get(values.size() - 1);
	}

	public List<PitchState> getValues() {
		return Collections.unmodifiableList(values);
	}
}
