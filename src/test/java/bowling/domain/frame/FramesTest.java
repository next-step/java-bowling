package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class FramesTest {

	Frames frames = new Frames();
	Frame frame = new Frame();

	@BeforeEach
	void setUp() {
		frames.upsert(frame);
	}

	@Nested
	class upsert {

		@Test
		@DisplayName("새로운 frame 인 경우, 추가한다.")
		void returnNew() {
			Frame newFrame = new Frame();
			frames.upsert(newFrame);

			assertThat(frames.getFrames()).hasSize(2);
		}

		@Test
		@DisplayName("기존에 있는 frame 인 경우, 추가하지 않는다.")
		void returnExist() {
			frames.upsert(frame);

			assertThat(frames.getFrames()).hasSize(1);
		}
	}
}