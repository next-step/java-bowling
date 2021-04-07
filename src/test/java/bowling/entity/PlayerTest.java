package bowling.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {

	@Test
	void player_test() {
		Player player = new Player("jyb");
		assertThat(player.currentPlayerFrameIndex())
			.isEqualTo(1);

		player.addPlayerFrameScore(8);
		assertThat(player.currentPlayerFrameIndex())
			.isEqualTo(1);

		player.addPlayerFrameScore(1);
		assertThat(player.currentPlayerFrameIndex())
			.isEqualTo(2);
	}
}
