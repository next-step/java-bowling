package bowling.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
		bowlGame.play(new Pin(3));

		assertThat(gameResult.findScore(0)).isEqualTo(new Pin(5));
		assertThat(gameResult.findScore(1)).isEqualTo(new Pin(3));
	}

	@Test
	@DisplayName("마지막 경기가 아닐시 총 프레임 합이 10 이상일시 예외가 발생한다.")
	public void checkNormalFrameScore() {
		BowlGame bowlGame = new BowlGame(1);
		bowlGame.play(new Pin(5));

		assertThatThrownBy(() -> bowlGame.play(new Pin(7)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("마지막 경기시 스트라이크나 스페어이면 최대20점 아니면 최대 10점이다.")
	public void checkFinalFrameScore() {
		BowlGame bowlGame = new BowlGame(10);
		bowlGame.play(new Pin(5));

		assertThatThrownBy(() -> bowlGame.play(new Pin(6)))
			.isInstanceOf(IllegalArgumentException.class);

		BowlGame bowlGame2 = new BowlGame(10);
		bowlGame2.play(new Pin(10));
		bowlGame2.play(new Pin(5));

		assertThatThrownBy(() -> bowlGame2.play(new Pin(6)))
			.isInstanceOf(IllegalArgumentException.class);

		BowlGame bowlGame3 = new BowlGame(10);
		bowlGame3.play(new Pin(4));
		bowlGame3.play(new Pin(6));

		assertThatThrownBy(() -> bowlGame3.play(new Pin(11)))
			.isInstanceOf(IllegalArgumentException.class);

	}
}