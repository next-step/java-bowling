package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.Index;
import bowling.domain.Pins;

class FramesTest {
	@DisplayName("초기 상태 검증")
	@Test
	void initialize() {
		// given
		Frames frames = Frames.initialize();

		// when
		int lastFrameIndex = frames.getLastFrameIndex();
		List<Frame> values = frames.getValues();

		// then
		assertAll(
			() -> assertThat(lastFrameIndex).isEqualTo(Index.MIN_OF_INDEX),
			() -> assertThat(values).hasSize(1)
		);

	}

	@DisplayName("프레임이 진행 상태이면 새로운 프레임이 추가되지 않는다")
	@Test
	void bowlWithoutAddFrame() {
		// given
		Frames frames = Frames.initialize();
		Pins pins = Pins.create(5);

		// when
		frames.bowl(pins);

		// then
		assertThat(frames.getValues()).hasSize(1);
	}

	@DisplayName("프레임이 종료상태로 변경되면 새로운 프레임이 추가된다")
	@Test
	void bowlWithAddFrame() {
		// given
		Frames frames = Frames.initialize();
		Pins pins = Pins.create(10);

		// when
		frames.bowl(pins);

		// then
		assertThat(frames.getValues()).hasSize(2);
	}

	@DisplayName("10프레임이면서 종료상태가 아니면 다음이 존재한다")
	@Test
	void hasNext() {
		// given
		Frames frames = Frames.initialize();

		// when
		boolean hasNext = frames.hasNext();

		// then
		assertThat(hasNext).isTrue();
	}
}
