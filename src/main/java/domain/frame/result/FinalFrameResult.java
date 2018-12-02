package domain.frame.result;

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
public class FinalFrameResult implements FrameResult {
	private int lastIndex = 0;
	private List<FrameResult> frameResults = new ArrayList<>();

	public FinalFrameResult() {
		frameResults.add(new None(Frame.MAX_FRAME));
	}

	@Override
	public int getFrameNumber() {
		return Frame.MAX_FRAME;
	}

	@Override
	public boolean isFinished() {
		// 전체 스트라이크일 경우 세번까지 칠 수 있다.
		if (isAllStrike() && frameResults.size() < 3) {
			return false;
		}

		//스페어일 경우 한번 더 칠 수 있다.
		if (containSpare() && frameResults.size() < 2) {
			return false;
		}

		return frameResults.stream().anyMatch(FrameResult::isFinished);
	}

	// 마지막 프레임의 점수는 보너스 계산 없이 단순 합으로 계산한다.
	@Override
	public Score getScore() {
		int totalScore = 0;
		for (FrameResult frameResult : frameResults) {
			totalScore += frameResult.getScore().toInteger();
		}
		return Score.of(totalScore);
	}

	@Override
	public Score calculateScore(Score previousScore) {
		Score score = previousScore;
		for (FrameResult frameResult : frameResults) {
			score = frameResult.calculateScore(score);
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
	public FrameResult tryBowl(Pin pin) {
		Preconditions.checkArgument(!isFinished());
		FrameResult lastResult = frameResults.get(lastIndex);
		if (lastResult.isFinished()) {
			return bowlNext(pin);
		}
		bowlCurrent(pin, lastResult);
		return this;
	}

	private void bowlCurrent(Pin pin, FrameResult lastResult) {
		lastResult = lastResult.tryBowl(pin);
		frameResults.remove(lastIndex);
		frameResults.add(lastIndex, lastResult);
	}

	private FrameResult bowlNext(Pin pin) {
		FrameResult frameResult = new None(Frame.MAX_FRAME);
		frameResult = frameResult.tryBowl(pin);
		frameResults.add(frameResult);
		lastIndex++;
		return this;
	}

	@Override
	public String toString() {
		StringJoiner stringJoiner = new StringJoiner("|");
		List<FrameResult> runningResult = getRunningResult();

		for (FrameResult frameResult : runningResult) {
			stringJoiner.add(frameResult.toString());
		}
		return stringJoiner.toString();
	}

	private List<FrameResult> getRunningResult() {
		return frameResults.stream()
			.filter(frameResult -> frameResult.getClass() != None.class)
			.collect(Collectors.toList());
	}
}
