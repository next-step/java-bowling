package bowling.domain.game;

import bowling.domain.DownedPinCount;
import bowling.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

	private final Player givenPlayer = new Player("TED");
	private Game givenGame;

	@BeforeEach
	void setup() {
		givenGame = new Game(givenPlayer);
	}

	@DisplayName("객체 생성 테스트")
	@Test
	void construct() {
		assertThat(givenGame).isEqualTo(new Game(givenPlayer));
	}

	@DisplayName("현재 게임 진행중인 프레임 번호 얻기 테스트")
	@Test
	void getCurrentFrameSequence() {
		assertThat(givenGame.getCurrentFrameSequence()).isEqualTo(1);
	}

	@DisplayName("게임 플레이 테스트")
	@Test
	void pitchTheBall() {
		givenGame.play(DownedPinCount.EIGHT);
		assertThat(givenGame.getCurrentFrameSequence()).isEqualTo(1);
		givenGame.play(DownedPinCount.TWO);
		assertThat(givenGame.getCurrentFrameSequence()).isEqualTo(2);
	}

	@DisplayName("10프레임까지 게임이 진행되었는지 확인이 가능한지 테스트")
	@Test
	void isGameFinished() {
		assertThat(givenGame.getCurrentFrameSequence()).isEqualTo(1);
		while (!givenGame.isGameFinished()) {
			givenGame.play(DownedPinCount.TEN);
		}
		assertThat(givenGame.getCurrentFrameSequence()).isEqualTo(10);
	}

}
