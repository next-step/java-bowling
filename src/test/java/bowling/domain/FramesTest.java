package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("프레임들")
class FramesTest {

	@DisplayName("[성공] 투구")
	@ParameterizedTest
	@CsvSource({
		"2,2",
		"3,2",
		"4,3",
		"5,3",
		"9,5",
		"10,6",
	})
	void pitch(final int pitchCount, final int expected) {
		// given
		final Frames frames = new Frames();

		// when
		for (int i = 0; i < pitchCount; i++) {
			frames.pitch(new Pitch(0));
		}

		// then
		assertThat(frames.currentIndex()).isEqualTo(expected);
	}
}
