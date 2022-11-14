package bowling.domain.frame;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

	@Nested
	class save {

		@Test
		@DisplayName("두번째 판에서 결과가 미스이면 새로운 frame 을 반환한다.")
		void isMiss() {
			FinalFrame frame = new FinalFrame();
			FinalFrame result = frame.save(1);
			result = result.save(2);

			assertThat(result).isNotEqualTo(frame);
		}

		@Test
		@DisplayName("사이즈가 3를 넘으면 새로운 frame 을 반환한다.")
		void overThree() {
			FinalFrame frame = new FinalFrame();
			FinalFrame result = frame.save(10);
			result = result.save(2);
			result = result.save(2);

			assertThat(result).isNotEqualTo(frame);
		}

		@Test
		@DisplayName("두번째 판에서 결과가 스페어이면 기존 frame 을 반환한다.")
		void spare() {
			FinalFrame frame = new FinalFrame();
			FinalFrame result = frame.save(1);
			result = result.save(9);

			assertThat(result).isEqualTo(frame);
		}

		@Test
		@DisplayName("처음 점수가 스트라이크면, 기존 frame 을 반환한다.")
		void strike() {
			FinalFrame frame = new FinalFrame();
			FinalFrame result = frame.save(10);
			result = result.save(2);

			assertThat(result).isEqualTo(frame);
		}
	}
}