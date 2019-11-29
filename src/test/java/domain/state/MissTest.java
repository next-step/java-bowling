package domain.state;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
class MissTest {

	@Test
	void 볼링핀_10개를_쓰러뜨렸으면_MISS가_될_수_없다() {
		// when & then
		assertThatThrownBy(() -> {
			Miss.of(10);
		})
		.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void MISS는_개수가_출력된다() {
		// given
		int bowlingPinsCount = 6;
		State state = Miss.of(bowlingPinsCount);

		// when
		String result = state.toSign();

		// then
		assertThat(result).isEqualTo(String.valueOf(bowlingPinsCount));
	}

}
