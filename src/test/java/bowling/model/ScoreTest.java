package bowling.model;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.model.frame.FinalFrame;
import bowling.model.frame.Frame;
import bowling.model.frame.NormalFrame;

public class ScoreTest {

	@Test
	@DisplayName("프레임의 정보를 입력하면 스코어가 생성된다.")
	public void createScore() {
		Score score = Score.createScore(getFrames());

		assertThat(score).isEqualTo(Score.createScore(getFrames()));
	}

	@Test
	@DisplayName("프레임의 정보를 입력하면 스코어 점수를 알수 있다.")
	public void getScore() {
		Score score = Score.createScore(getFrames());

		assertThat(score.getScore()).containsExactly(9, 18, -1);
	}

	private List<Frame> getFrames() {
		List<Frame> frames = new ArrayList<>();
		frames.add(getNormalFrame(1, 7, 2));
		frames.add(getNormalFrame(2, 8, 1));
		frames.add(getFinalFrame());
		IntStream.range(0, 2)
			.forEach(i -> frames.get(i).bringNextFrame(frames.get(i + 1)));
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
