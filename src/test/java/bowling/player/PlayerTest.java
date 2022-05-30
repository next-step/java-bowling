package bowling.player;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("플레이어 테스트")
class PlayerTest {

	@Test
	void 플레이어의_이름을_Wrapping_하는_객체_생성() {
		assertThat(new Player("AAA")).isEqualTo(new Player("AAA"));
	}

	@ParameterizedTest(name = "{displayName} : {arguments}")
	@NullAndEmptySource
	void 플레이어의_이름이_널이거나_공백이면_예외(String name) {
		assertThatIllegalArgumentException().isThrownBy(
			() -> new Player(name)
		);
	}

	@ParameterizedTest(name = "{displayName} : {arguments}")
	@ValueSource(strings = {"AA", "AAAA"})
	void 플레이어의_이름이_기준_글자_수와_다르면_예외(String name) {
		assertThatIllegalArgumentException().isThrownBy(
			() -> new Player(name)
		);
	}

}