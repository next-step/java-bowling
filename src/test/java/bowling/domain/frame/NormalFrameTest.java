package bowling.domain.frame;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

	@Nested
	class save {

		@Test
		@DisplayName("점수가 스트라이크이면 새로운 frame 을 반환한다.")
		void strike() {
			NormalFrame normalFrame = new NormalFrame();
			NormalFrame result = normalFrame.save(10);

			assertThat(result).isNotEqualTo(normalFrame);
		}

		@Test
		@DisplayName("사이즈가 2를 넘으면 새로운 frame 을 반환한다.")
		void overTwo() {
			NormalFrame normalFrame = new NormalFrame();
			NormalFrame result = normalFrame.save(1);
			result = result.save(2);

			assertThat(result).isNotEqualTo(normalFrame);
		}

		@Test
		@DisplayName("스트라이크가 아니면서, 사이즈가 2보다 작을 경우 존재하는 frame 을 반환한다.")
		void returnExist() {
			NormalFrame normalFrame = new NormalFrame();
			NormalFrame result = normalFrame.save(1);

			assertThat(result).isEqualTo(normalFrame);
		}
	}
}