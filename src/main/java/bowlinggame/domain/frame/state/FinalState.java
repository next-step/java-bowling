package bowlinggame.domain.frame.state;

import bowlinggame.domain.frame.Score;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalState implements State {

	private LinkedList<State> states = new LinkedList<>();

	public FinalState() {
		ready();
	}

	@Override
	public State roll(int pinCount) {
		State currentState = getCurrent();
		State nextState = currentState.roll(pinCount);
		states.remove(currentState);
		states.addLast(nextState);
		return nextState;
	}

	private State getCurrent() {
		State currentState = states.getLast();
		if (currentState.isFinished()) {
			return ready();
		}
		return currentState;
	}

	@Override
	public Score getScore() {
		Score score = states.getFirst().getScore();
		for (int index = 1; index < states.size(); index++) {
			score = states.get(index).calculateBonus(score);
		}
		return score;
	}

	@Override
	public Score calculateBonus(Score score) {
		for (State state : states) {
			score = state.calculateBonus(score);
		}
		return score;
	}

	@Override
	public boolean isFinished() {
		return states.getFirst().isFinished()
				&& !getScore().hasBonus();
	}

	@Override
	public String getResult() {
		return states.stream()
				.map(State::getResult)
				.collect(Collectors.joining("|"));
	}

	private State ready() {
		State ready = new Ready();
		states.addLast(ready);
		return ready;
	}
}
