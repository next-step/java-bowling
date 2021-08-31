package bowling.model;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.model.frame.FinalFrame;
import bowling.model.frame.Frame;
import bowling.model.frame.NormalFrame;

public class StateCalculatorTest {

	@Test
	@DisplayName("프레임의 정보를 입력하면 스코어가 생성된다.")
	public void createScore() {
		ScoreCalculator scoreCalculator = ScoreCalculator.createScore(getFrames());

		assertThat(scoreCalculator).isEqualTo(ScoreCalculator.createScore(getFrames()));
	}

	@Test
	@DisplayName("프레임의 정보를 입력하면 스코어 점수를 알수 있다.")
	public void getScore() {
		ScoreCalculator scoreCalculator = ScoreCalculator.createScore(getFrames());

		assertThat(scoreCalculator.getScore()).containsExactly(9, 18, -1);
	}

	private List<Frame> getFrames() {
		List<Frame> frames = new ArrayList<>();
		frames.add(getNormalFrame(1, 7, 2));
		frames.add(getNormalFrame(2, 8, 1));
		frames.add(getFinalFrame());
		return frames;
	}

	private Frame getNormalFrame(int frameNumber, int firstPin, int secondPin) {
		NormalFrame normalFrame = new NormalFrame(frameNumber);
		normalFrame.playGame(firstPin);
		normalFrame.playGame(secondPin);
		return normalFrame;
	}

	private Frame getFinalFrame() {
		FinalFrame finalFrame = new FinalFrame(10);
		finalFrame.playGame(6);
		finalFrame.playGame(4);
		return finalFrame;
	}

}
