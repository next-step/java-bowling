package bowling.domain.player;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

	@DisplayName("플레이어의 이름이 영어로 된 3글자라면 새로운 플레이어가 생성된다.")
	@ValueSource(strings = {"PJS", "abc", "pjs", "sjd"})
	@ParameterizedTest
	void 플레이어의_이름은_영문_세_글자이다(String name) {
		assertThatCode(
			() -> Player.ofName(name)
		).doesNotThrowAnyException();
	}

	@DisplayName("플레이어의 이름이 영어로 된 3글자가 아니라면 오류가 발생한다.")
	@ValueSource(strings = {"123", "가나다", "hi", "CoVID"})
	@ParameterizedTest
	void 프레이어의_이름이_오류를_반환한다(String name) {
		assertThatCode(
			() -> Player.ofName(name))
			.as("wrong name")
			.isInstanceOf(IllegalArgumentException.class);
	}
}
