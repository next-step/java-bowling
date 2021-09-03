package bowling.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BowlGameTest {

	@Test
	@DisplayName("볼링게임을 생성한다.")
	public void createBowlGame() {
		BowlGame bowlGame = BowlGame.createBowlGame(getPlayers());

		assertThat(bowlGame).isEqualTo(BowlGame.createBowlGame(getPlayers()));
	}

	@Test
	@DisplayName("볼링게임 진행시 전체 게임의 진행유무를 알 수 있다.")
	public void isContinueGame() {
		BowlGame bowlGame = BowlGame.createBowlGame(getPlayers());

		assertThat(bowlGame.isContinueGame()).isTrue();
	}

	@Test
	@DisplayName("볼링게임 진행시 현재 플레이어를 알 수 있다.")
	public void FindTurnPlayer() {
		BowlGame bowlGame = BowlGame.createBowlGame(getPlayers());

		assertThat(bowlGame.turnPlayer()).isEqualTo(new Player(new Name("aaa")));
	}

	@Test
	@DisplayName("볼링게임 진행시 플레이어의 프레임별 종료유무를 알 수 있다.")
	public void playBowling() {
		BowlGame bowlGame = BowlGame.createBowlGame(getPlayers());

		assertAll(
			() -> assertThat(bowlGame.playBowling(10)).isTrue(),
			() -> assertThat(bowlGame.playBowling(5)).isFalse()
		);
	}

	@Test
	@DisplayName("볼링게임의 종료시 플레이어 턴을 조회 가능하다.")
	public void findTurnOfPlay() {
		BowlGame bowlGame = BowlGame.createBowlGame(getPlayers());

		assertAll(
			() -> assertThat(bowlGame.findTurnOfPlay(0)).isEqualTo(1),
			() -> assertThat(bowlGame.findTurnOfPlay(1)).isEqualTo(0)
		);
	}

	private String[] getPlayers() {
		String[] players = new String[2];
		players[0] = "aaa";
		players[1] = "bbb";
		return players;
	}
}