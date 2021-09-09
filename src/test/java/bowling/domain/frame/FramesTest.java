package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("프레임들")
class FramesTest {

	@DisplayName("전체 프레임 종료")
	@Test
	void isFinish() {
		// given

		// when
		final boolean result = Frames.of().isFinish();

		// then
		assertThat(result).isFalse();
	}
}
