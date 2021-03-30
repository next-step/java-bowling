package bowling.entity;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FrameScoreTest {

	@Test
	@DisplayName("Frame 성공 Strike")
	void frame_success() {
		// when
		NormalFrameScore normalFrameScore = NormalFrameScore.ofFirst(10);

		// then
		assertThat(normalFrameScore.isKeepGoing())
			.isEqualTo(false);
	}

	@ParameterizedTest
	@DisplayName("첫번째 frame strike 아닌경우")
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
	void frame_not_strike(int input) {
		// when
		NormalFrameScore normalFrameScore = NormalFrameScore.ofFirst(input);

		// then
		assertThat(normalFrameScore.isKeepGoing())
			.isEqualTo(true);
	}

	@ParameterizedTest
	@DisplayName("두번째 frame")
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
	void frame_secondFrame(int input) {
		// when
		NormalFrameScore first = NormalFrameScore.ofFirst(input);

		// then
		assertThat(NormalFrameScore.ofSecond(first, 10 - input))
			.isInstanceOf(NormalFrameScore.class);
	}
}
