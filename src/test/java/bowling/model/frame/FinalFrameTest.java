package bowling.model.frame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.model.GameResult;
import bowling.model.Pin;

class FinalFrameTest {

	@Test
	@DisplayName("마지막 프레임을 생성한다.")
	public void createFinalFrame() {
		FinalFrame finalFrame = new FinalFrame(10);

		assertThat(finalFrame).isEqualTo(new FinalFrame(10));
	}

	@ParameterizedTest
	@DisplayName("마지막 프레임은 10만 생성 할 수 있다.")
	@CsvSource(value = {"9", "11"})
	public void checkFrameNumber(int frameNumber) {
		assertThatThrownBy(() -> new FinalFrame(frameNumber))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("마지막 프레임 게임시 게임 정보를 알 수 있다.")
	public void playBowlingGame() {
		FinalFrame finalFrame = new FinalFrame(10);
		GameResult gameResult = finalFrame.playGame(5);

		assertAll(
			() -> assertThat(gameResult.findTotalScore()).isEqualTo(5),
			() -> assertThat(gameResult.findScore(0)).isEqualTo(new Pin(5))
		);
	}

	@Test
	@DisplayName("마지막 프레임 게임시 게임 종료 정보를 알 수 있다.")
	public void isEndGame() {
		FinalFrame finalFrame = new FinalFrame(10);
		finalFrame.playGame(5);

		assertAll(
			() -> assertThat(finalFrame.isGameEnd()).isFalse(),
			() -> {
				finalFrame.playGame(3);
				assertThat(finalFrame.isGameEnd()).isTrue();
			});
	}

	@Test
	@DisplayName("마지막 프레임 게임시 게임 종료 후 게임시 예외가 발생된다.")
	public void checkIsEndGame() {
		FinalFrame finalFrame = new FinalFrame(10);
		finalFrame.playGame(5);
		finalFrame.playGame(3);

		assertThatThrownBy(() -> finalFrame.playGame(3))
			.isInstanceOf(IllegalStateException.class);
	}

}