package domain.state;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
class ReadyTest {

	@Test
	void Ready는_빈칸이_출력된다() {
		// given
		State state = Ready.getInstance();

		// when & then
		assertThat(state.toSign()).isEqualTo(" ");
	}

}
