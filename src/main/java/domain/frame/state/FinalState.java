package domain.frame.state;

import com.google.common.base.Preconditions;
import domain.Pin;
import domain.Score;
import domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Created by hspark on 22/11/2018.
 */
public class FinalState implements State {
	private int lastIndex = 0;
	private List<State> states = new ArrayList<>();

	public FinalState() {
		states.add(new None(Frame.MAX_FRAME));
	}

	@Override
	public int getFrameNumber() {
		return Frame.MAX_FRAME;
	}

	@Override
	public boolean isFinished() {
		// 전체 스트라이크일 경우 세번까지 칠 수 있다.
		if (isAllStrike() && states.size() < 3) {
			return false;
		}

		//스페어일 경우 한번 더 칠 수 있다.
		if (containSpare() && states.size() < 2) {
			return false;
		}

		return states.stream().anyMatch(State::isFinished);
	}

	// 마지막 프레임의 점수는 보너스 계산 없이 단순 합으로 계산한다.
	@Override
	public Score getScore() {
		int totalScore = 0;
		for (State state : states) {
			totalScore += state.getScore().toInteger();
		}
		return Score.of(totalScore);
	}

	@Override
	public Score calculateScore(Score previousScore) {
		Score score = previousScore;
		for (State state : states) {
			score = state.calculateScore(score);
		}
		return score;
	}

	private boolean containSpare() {
		return getRunningResult().get(0).getClass() == Spare.class;
	}

	private boolean isAllStrike() {
		return getRunningResult().stream().allMatch(frameResult -> frameResult.getClass() == Strike.class);
	}

	@Override
	public State tryBowl(Pin pin) {
		Preconditions.checkArgument(!isFinished());
		State lastResult = states.get(lastIndex);
		if (lastResult.isFinished()) {
			return bowlNext(pin);
		}
		bowlCurrent(pin, lastResult);
		return this;
	}

	private void bowlCurrent(Pin pin, State lastResult) {
		lastResult = lastResult.tryBowl(pin);
		states.remove(lastIndex);
		states.add(lastIndex, lastResult);
	}

	private State bowlNext(Pin pin) {
		State state = new None(Frame.MAX_FRAME);
		state = state.tryBowl(pin);
		states.add(state);
		lastIndex++;
		return this;
	}

	@Override
	public String toString() {
		StringJoiner stringJoiner = new StringJoiner("|");
		List<State> runningResult = getRunningResult();

		for (State state : runningResult) {
			stringJoiner.add(state.toString());
		}
		return stringJoiner.toString();
	}

	private List<State> getRunningResult() {
		return states.stream()
			.filter(frameResult -> frameResult.getClass() != None.class)
			.collect(Collectors.toList());
	}
}
