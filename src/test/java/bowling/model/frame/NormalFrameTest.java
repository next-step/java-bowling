package bowling.model.frame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.model.Pin;

class NormalFrameTest {

	@Test
	@DisplayName("노멀 프레임을 생성한다.")
	public void createNormalFrame() {
		NormalFrame normalFrame = new NormalFrame(1);

		assertThat(normalFrame).isEqualTo(new NormalFrame(1));
	}

	@ParameterizedTest
	@DisplayName("노멀 프레임은 1~9까지만 생성 할 수 있다.")
	@CsvSource(value = {"0", "10"})
	public void checkFrameNumber(int frameNumber) {
		assertThatThrownBy(() -> new NormalFrame(frameNumber))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("노멀 프레임 게임시 게임 정보를 알 수 있다.")
	public void playBowlingGame() {
		NormalFrame normalFrame = new NormalFrame(1);
		normalFrame.playGame(5);

		assertThat(normalFrame.playResult.findScore(0)).isEqualTo(new Pin(5));
	}

	@Test
	@DisplayName("노멀 프레임 스트라이크가 아닐시 게임 종료 정보를 알 수 있다.")
	public void isNotStrikeEndGame() {
		NormalFrame normalFrame = new NormalFrame(1);
		normalFrame.playGame(5);

		assertAll(
			() -> assertThat(normalFrame.isGameEnd()).isFalse(),

			() -> {
				normalFrame.playGame(3);
				assertThat(normalFrame.isGameEnd()).isTrue();
			}
		);
	}

	@Test
	@DisplayName("노멀 프레임의 게임이 스트라이크 일시 게임 종료 정보를 알 수 있다.")
	public void isStrikeEndGame() {
		NormalFrame normalFrame2 = new NormalFrame(1);
		normalFrame2.playGame(10);

		assertThat(normalFrame2.isGameEnd()).isTrue();
	}
}