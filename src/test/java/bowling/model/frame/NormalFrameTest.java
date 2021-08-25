package bowling.model.frame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.model.GameResult;
import bowling.model.Pin;

class NormalFrameTest {

	@Test
	@DisplayName("노멀 프레임을 생성한다.")
	public void createNormalFrame() {
		NormalFrame normalFrame = new NormalFrame(1);

		assertThat(normalFrame).isEqualTo(new NormalFrame(1));
	}

	@Test
	@DisplayName("노멀 프레임은 1~9까지만 생성 할 수 있다.")
	public void checkFrameNumber() {
		assertThatThrownBy(() -> new NormalFrame(0))
			.isInstanceOf(IllegalArgumentException.class);

		assertThatThrownBy(() -> new NormalFrame(10))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("노멀 프레임 게임시 게임 정보를 알 수 있다.")
	public void playBowlingGame() {
		NormalFrame normalFrame = new NormalFrame(1);
		GameResult gameResult = normalFrame.playGame(5);

		assertThat(gameResult.findTotalScore()).isEqualTo(5);
		assertThat(gameResult.findScore(0)).isEqualTo(new Pin(5));
	}

	@Test
	@DisplayName("노멀 프레임 게임시 게임 종료 정보를 알 수 있다.")
	public void isEndGame() {
		NormalFrame normalFrame = new NormalFrame(1);
		normalFrame.playGame(5);

		assertThat(normalFrame.isGameEnd()).isFalse();

		normalFrame.playGame(3);
		assertThat(normalFrame.isGameEnd()).isTrue();

		NormalFrame normalFrame2 = new NormalFrame(1);
		normalFrame2.playGame(10);

		assertThat(normalFrame2.isGameEnd()).isTrue();
	}

	@Test
	@DisplayName("노멀 프레임 게임시 게임 종료 후 게임시 예외가 발생된다.")
	public void checkIsEndGame() {
		NormalFrame normalFrame = new NormalFrame(1);
		normalFrame.playGame(5);
		normalFrame.playGame(3);

		assertThatThrownBy(() -> normalFrame.playGame(3))
			.isInstanceOf(IllegalStateException.class);
	}
}