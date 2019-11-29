package domain.state;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
class SpareTest {

	@Test
	void 볼링핀_10개를_쓰러뜨렸으면_SPARE가_될_수_없다() {
		// when & then
		assertThatThrownBy(() -> {
			Spare.of(10);
		})
		.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void SPARE는_슬래시가_출력된다() {
		// given
		int bowlingPinsCount = 6;
		State state = Spare.of(bowlingPinsCount);

		// when
		String result = state.toSign();

		// then
		assertThat(result).isEqualTo("/");
	}

}
