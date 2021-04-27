package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FramesTest {
	@Test
	@DisplayName("Frames 초기화 테스트")
	void init_frames_test() {
		Frames frames = Frames.init();
		int frameSize = frames.size();
		assertThat(frameSize).isEqualTo(10);
	}
}
