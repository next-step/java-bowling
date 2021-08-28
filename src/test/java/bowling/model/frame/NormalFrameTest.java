package bowling.model.frame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

		assertThat(normalFrame.playResult.findScore(0)).isEqualTo(5);
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

	@Test
	@DisplayName("노멀 프레임이 스트라이크나 스페어가 아니면 -1을 반환한다.")
	public void getNotStrikeAndNotSpare() {
		NormalFrame normalFrame = new NormalFrame(1);
		int result = normalFrame.getStrikeAndSpareNextScore(false, false, 10);

		assertThat(result).isEqualTo(-1);
	}

	@Test
	@DisplayName("노멀 프레임이 스페어이면 이전점수와 해당 프레임의 첫번째 점수를 합산한다.")
	public void getSpareByNextScore() {
		NormalFrame normalFrame = new NormalFrame(1);
		normalFrame.playGame(5);
		int result = normalFrame.getStrikeAndSpareNextScore(false, true, 10);

		assertThat(result).isEqualTo(15);
	}

	@Test
	@DisplayName("노멀 프레임이 스페어이면 아전과 해당 프레임 합산점수 와 다음 프레임의 첫번째 점수를 합산한다.")
	public void getStrikeByNextScore() {
		NormalFrame normalFrame2 = new NormalFrame(2);
		normalFrame2.playGame(6);

		NormalFrame normalFrame = new NormalFrame(1);
		normalFrame.playGame(5);
		normalFrame.playGame(5);
		normalFrame.bringNextFrame(normalFrame2);
		int result = normalFrame.getStrikeAndSpareNextScore(true, false, 10);

		assertThat(result).isEqualTo(26);
	}

	@Test
	@DisplayName("노멀프레임 점수중 스트라이크 스페어가 아니면 해당 점수만 계산한다.")
	public void getGameScoreNotStrikeAndSpare() {
		NormalFrame normalFrame = new NormalFrame(1);
		normalFrame.playGame(5);
		normalFrame.playGame(4);

		assertThat(normalFrame.getGameScore()).isEqualTo(9);
	}

	@Test
	@DisplayName("노멀레임 점수중 스페어이면 다음 프레임 1번공의 점수와 합산을 계산한다.")
	public void getGameScoreBySpare() {
		NormalFrame normalFrame2 = new NormalFrame(2);
		normalFrame2.playGame(6);

		NormalFrame normalFrame = new NormalFrame(1);
		normalFrame.playGame(4);
		normalFrame.playGame(6);
		normalFrame.bringNextFrame(normalFrame2);

		assertThat(normalFrame.getGameScore()).isEqualTo(16);
	}

	@Test
	@DisplayName("노멀레임 점수중 스트라이크이면 다다음 프레임 1번공의 점수와 합산을 계산한다.")
	public void getGameScoreByStrike() {
		NormalFrame normalFrame3 = new NormalFrame(3);
		normalFrame3.playGame(9);

		NormalFrame normalFrame2 = new NormalFrame(2);
		normalFrame2.playGame(6);
		normalFrame2.playGame(4);
		normalFrame2.bringNextFrame(normalFrame3);

		NormalFrame normalFrame = new NormalFrame(1);
		normalFrame.playGame(10);
		normalFrame.bringNextFrame(normalFrame2);

		assertThat(normalFrame.getGameScore()).isEqualTo(29);
	}

	@Test
	@DisplayName("노멀레임 점수중 더블이면 다다음 프레임 1번공의 점수와 합산을 계산한다.")
	public void getGameScoreByDouble() {
		NormalFrame normalFrame3 = new NormalFrame(3);
		normalFrame3.playGame(9);

		NormalFrame normalFrame2 = new NormalFrame(2);
		normalFrame2.playGame(10);
		normalFrame2.bringNextFrame(normalFrame3);

		NormalFrame normalFrame = new NormalFrame(1);
		normalFrame.playGame(10);
		normalFrame.bringNextFrame(normalFrame2);

		assertThat(normalFrame.getGameScore()).isEqualTo(29);
	}

}