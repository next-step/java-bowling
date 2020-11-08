package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FramesTest {

	@Test
	void 프레임의_일급_컬렉션을_만든다() {
		Frames frames = new Frames();
		assertThat(frames).isEqualTo(new Frames());
	}

	@ParameterizedTest
	@CsvSource(value = {"5, 5, 1", "10, 10, 2"}) // the cases for non-strike and strike
	void 스트라이크_시의_프레임_수를_반환한다(int first, int second, int expect) {
		Frames frames = new Frames();
		frames.pitch(first);
		frames.pitch(second);
		assertThat(frames.getFrames()).hasSize(expect);
	}

	@Test
	void 마지막_프레임의_개수를_확인한다() {
		Frames frames = new Frames();
		for (int i = 0; i < 10; i++) {
			frames.pitch(10);
		}
		assertThat(frames.getFrames()).hasSize(10);
	}
}
