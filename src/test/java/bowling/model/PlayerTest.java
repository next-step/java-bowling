package bowling.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.model.frame.Frames;

class PlayerTest {

	@Test
	@DisplayName("플레이어는 이름을 가지고 생성한다.")
	public void createPlayer() {
		Player player = new Player(new Name("abc"));

		Assertions.assertAll(
			() -> assertThat(player).isEqualTo(new Player(new Name("abc"))),
			() -> assertThat(player.getPlayerName()).isEqualTo("abc")
		);
	}

	@Test
	@DisplayName("플레이어에 게임의 프레임즈를 가지고 생성한다.")
	public void createPlayerByFrames() {
		Player player = new Player(new Name("abc"), Frames.initCreateFrames());

		assertThat(player).isEqualTo(new Player(new Name("abc"), Frames.initCreateFrames()));
	}

}