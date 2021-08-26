package bowling.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BowlGameTest {

	@Test
	@DisplayName("볼링 게임을 생성한다.")
	public void createBowlGame() {
		BowlGame bowlGame = new BowlGame(1);

		assertThat(bowlGame).isEqualTo(new BowlGame(1));
	}

	@Test
	@DisplayName("플레이시 플레이 결과를 저장한다.")
	public void playGame() {
		BowlGame bowlGame = new BowlGame(1);
		GameResult gameResult = bowlGame.play(new Pin(5));
		bowlGame.play(3);

		assertAll(
			() -> assertThat(gameResult.findScore(0)).isEqualTo(new Pin(5)),
			() -> assertThat(gameResult.findScore(1)).isEqualTo(new Pin(3))
		);
	}

	@Test
	@DisplayName("마지막 경기가 아닐시 총 프레임 합이 10 이상일시 예외가 발생한다.")
	public void checkNormalFrameScore() {
		BowlGame bowlGame = new BowlGame(1);
		bowlGame.play(5);

		assertThatThrownBy(() -> bowlGame.play(new Pin(7)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("마지막 경기시 스트라이크 후 핀의 개수가 초가화면 예외가 발생한다.")
	public void checkFirstStrikeOverNumber() {
		BowlGame bowlGame = new BowlGame(10);
		bowlGame.play(5);

		assertThatThrownBy(() -> bowlGame.play(6))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@DisplayName("마지막 볼링의 경기 수가 초과하면 예외가 발생한다.")
	@CsvSource(value = {"10,10,5,6", "10,4,6,11"})
	public void checkFirstAndSecondStrikeOverPlay(int firstPin, int secondPin, int bonusPin, int overPin) {
		BowlGame bowlGame2 = new BowlGame(firstPin);
		bowlGame2.play(secondPin);
		bowlGame2.play(bonusPin);

		assertThatThrownBy(() -> bowlGame2.play(new Pin(overPin)))
			.isInstanceOf(IllegalArgumentException.class);
	}

}