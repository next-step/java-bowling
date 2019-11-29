package domain.state;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
class StrikeTest {

	@Test
	void Strike는_X가_출력된다() {
		// given
		State state = Strike.getInstance();

		// when & then
		assertThat(state.toSign()).isEqualTo("X");
	}

}
