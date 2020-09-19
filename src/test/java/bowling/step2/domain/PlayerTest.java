package bowling.step2.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

	@DisplayName(value = "플레이어명 3글자일 경우 정상 생성")
	@Test
	public void createPlayer() {
		assertThat(new Player("son")).hasFieldOrPropertyWithValue("name", "son");
	}

	@DisplayName(value = "플레이어명 3글자 넘을 경우 에러 발생")
	@Test
	public void playerNameLength() {
		assertThatIllegalArgumentException().isThrownBy(() -> new Player("DAMI"))
				.withMessage("플레이어 이름은 3글자만 가능합니다.");
	}
}
