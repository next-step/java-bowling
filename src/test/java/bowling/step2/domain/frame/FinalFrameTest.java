package bowling.step2.domain.frame;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {
	private Frame frame;
	@Before
	public void setUp() {
		frame = new FinalFrame();
	}

	@DisplayName(value = "마지막 프레임 미스 결과")
	@Test
	public void finalFrameBowlingMiss() {
		frame = frame.bowling(5).bowling(1);
		assertThat(frame.isGameOver()).isTrue();
		assertThat(frame.makeBoard().getResults()).hasToString("[5|1]");
	}

	@DisplayName(value = "마지막 프레임 스트라이크 결과")
	@Test
	public void finalFrameBowlingStrike() {
		frame = frame.bowling(10);
		assertThat(frame.isGameOver()).isFalse();
		assertThat(frame.makeBoard().getResults()).hasToString("[X]");

		frame = frame.bowling(3);
		assertThat(frame.isGameOver()).isTrue();
		assertThat(frame.makeBoard().getResults()).hasToString("[X|3]");
	}

	@DisplayName(value = "마지막 프레임 스페어 결과")
	@Test
	public void finalFrameBowlingSpare() {
		frame = frame.bowling(2).bowling(8);
		assertThat(frame.isGameOver()).isFalse();
		assertThat(frame.makeBoard().getResults()).hasToString("[2|/]");

		frame = frame.bowling(3);
		assertThat(frame.isGameOver()).isTrue();
		assertThat(frame.makeBoard().getResults()).hasToString("[2|/|3]");
	}

}
