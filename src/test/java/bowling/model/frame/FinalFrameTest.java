package bowling.model.frame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
		finalFrame.playGame(5);

		assertAll(
			() -> assertThat(finalFrame.playResult.findTotalScore()).isEqualTo(5),
			() -> assertThat(finalFrame.playResult.findScore(0)).isEqualTo(5)
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
	@DisplayName("마지막프레임의 이전 프레임이 스트라이크이면 2번째공의 합산의 합을 반환한다.")
	public void getSpareNextScore() {
		FinalFrame finalFrame = new FinalFrame(10);
		finalFrame.playGame(5);
		finalFrame.playGame(4);
		int score = finalFrame.getStrikeAndSpareNextScore(2, 10);

		assertThat(score).isEqualTo(19);
	}

	@Test
	@DisplayName("마지막프레임의 이전 프레임이 스페어이면 1번째공의 합산의 합을 반환한다.")
	public void getStrikeNextScore() {
		FinalFrame finalFrame = new FinalFrame(10);
		finalFrame.playGame(5);
		int score = finalFrame.getStrikeAndSpareNextScore(1, 10);

		assertThat(score).isEqualTo(15);
	}

	@Test
	@DisplayName("마지막프레임은 해당 점수만 계산한다.")
	public void getGameScore() {
		FinalFrame finalFrame = new FinalFrame(10);
		finalFrame.playGame(5);
		finalFrame.playGame(4);

		assertThat(finalFrame.getGameScore()).isEqualTo(9);
	}

}