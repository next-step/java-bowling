package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.score.Score;

public class NormalFrameTest {

	private NormalFrame frame;
	private FinalFrame finalFrame;

	@BeforeEach
	void setUp() {
		frame = NormalFrame.createFirstFrame();
		finalFrame = FinalFrame.of();
	}

	@DisplayName("새로 추가한 프레임의 경우 게임을 추가로 플레이할 수 있다.")
	@Test
	void 새로_추가한_프레임은_게임_플레이가_가능하다() {
		boolean canPlayMoreCertain = frame.canPlayMore();
		assertThat(canPlayMoreCertain).isEqualTo(true);

		boolean canPlayMoreCertainForFinalFrame = finalFrame.canPlayMore();
		assertThat(canPlayMoreCertainForFinalFrame).isEqualTo(true);
	}

	@DisplayName("한 타구가 진행된 프레임의 경우 게임을 추가로 플레이할 수 있다.")
	@Test
	void 한_타구가_진행된_프레임은_게임_플레이가_가능하다() {
		Score firstScore = Score.ofScore(5);

		frame.addScore(firstScore);
		boolean canPlayMoreCertain = frame.canPlayMore();
		assertThat(canPlayMoreCertain).isEqualTo(true);

		finalFrame.addScore(firstScore);
		boolean canPlayMoreCertainForFinalFrame = finalFrame.canPlayMore();
		assertThat(canPlayMoreCertainForFinalFrame).isEqualTo(true);
	}

	@DisplayName("두 번째 타구가 진행된 프레임의 경우 게임을 추가로 플레이할 수 없다.")
	@Test
	void 두번째_타구가_진행된_프레임은_게임_플레이가_불가능하다() {
		Score firstScore = Score.ofScore(5);
		Score secondScore = Score.ofScore(4);

		frame.addScore(firstScore);
		frame.addScore(secondScore);
		boolean canPlayMoreCertain = frame.canPlayMore();
		assertThat(canPlayMoreCertain).isEqualTo(false);

		finalFrame.addScore(firstScore);
		finalFrame.addScore(secondScore);
		boolean canPlayMoreCertainForFinalFrame = finalFrame.canPlayMore();
		assertThat(canPlayMoreCertainForFinalFrame).isEqualTo(false);
	}
}
