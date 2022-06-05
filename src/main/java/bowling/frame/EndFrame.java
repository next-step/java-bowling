package bowling.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import bowling.score.Score;
import bowling.state.Initialized;
import bowling.state.Open;
import bowling.state.State;

public class EndFrame implements Frame {

	private static final int MIN_TRY_COUNT = 2;
	private static final int MAX_TRY_COUNT = 3;
	private static final int FIRST_INDEX = 0;
	private static final int SECOND_INDEX = 1;

	private final List<State> states = new ArrayList<>();
	private int tryCount;

	public EndFrame() {
		this.states.add(Initialized.getInstance());
		this.tryCount = 0;
	}

	@Override
	public boolean isEnd() {
		if (tryCount == MAX_TRY_COUNT) {
			return true;
		}
		return tryCount == MIN_TRY_COUNT && lastState() instanceof Open;
	}

	@Override
	public void throwBowl(int throwCount) {

		if (isEnd()) {
			throw new IllegalStateException("마지막 프레임은 종료되고 투구될 수 없습니다.");
		}

		tryCount++;

		State lastState = lastState();
		if (lastState.isEnd()) {
			addState(throwCount);
			return;
		}

		updateCurrentState(throwCount);
	}

	private void addState(int throwCount) {
		states.add(Initialized.getInstance().throwBowl(throwCount));
	}

	private void updateCurrentState(int throwCount) {
		State lastState = lastState();
		states.remove(lastState);
		states.add(lastState.throwBowl(throwCount));
	}

	private State lastState() {
		return this.states.get(this.states.size() - 1);
	}

	@Override
	public Optional<Frame> nextFrame() {
		return Optional.empty();
	}

	@Override
	public int number() {
		return MAX_FRAME_NUMBER;
	}

	@Override
	public String symbol() {
		return states.stream()
			.map(State::symbol)
			.collect(Collectors.joining("|"));
	}

	@Override
	public Score score() {
		if (!isEnd()) {
			return Score.unavailable();
		}

		int sum = states.stream()
			.mapToInt(state -> state.score().getValue())
			.sum();

		return Score.of(sum, 0);
	}

	@Override
	public Score bonus(Score previousScore) {
		try {
			State firstState = states.get(FIRST_INDEX);

			Score currentScore = firstState.bonus(previousScore);
			if (currentScore.canScore()) {
				return currentScore;
			}

			State secondState = states.get(SECOND_INDEX);
			return secondState.bonus(currentScore);
		} catch (UnsupportedOperationException | IndexOutOfBoundsException e) {
			return Score.unavailable();
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		EndFrame endFrame = (EndFrame)o;
		return tryCount == endFrame.tryCount && Objects.equals(states, endFrame.states);
	}

	@Override
	public int hashCode() {
		return Objects.hash(states, tryCount);
	}
}
