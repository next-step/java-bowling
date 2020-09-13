package bowling.domain.game;

import bowling.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

	@DisplayName("객체 생성 테스트")
	@Test
	void construct() {
		Player givenPlayer = new Player("TED");
		assertThat(new Game(givenPlayer)).isEqualTo(new Game(givenPlayer));
	}

}
