package bowling.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	@DisplayName("플레이어는 이름을 가지고 생성한다.")
	public void createPlayer() {
		Player player = new Player(new Name("abc"));

		assertThat(player).isEqualTo(new Player(new Name("abc")));
		assertThat(player.getPlayerName()).isEqualTo("abc");
	}

}