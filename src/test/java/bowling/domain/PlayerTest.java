package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

	@Test
	void 새로운_플레이어를_생성한다(){
		Player player = new Player("JYP");
		assertThat(player).isEqualTo(new Player("JYP"));
	}

	@ParameterizedTest
	@ValueSource(strings = {"박 일병", "GeneralPark"})
	void 올바르지_않은_플레이어_이름에는_오류를_반환한다(String name) {
		assertThatThrownBy(() -> new Player(name))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
