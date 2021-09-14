package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.common.Pins;

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

	static Stream<Arguments> addFrame() {
		return Stream.of(
			Arguments.of(Frames.of(new ArrayList<Frame>() {{
				add(LastFrame.of());
			}}), Pins.of(10), 1),
			Arguments.of(Frames.of(new ArrayList<Frame>() {{
				add(BaseFrame.of());
			}}), Pins.of(10), 2),
			Arguments.of(Frames.of(new ArrayList<Frame>() {{
				add(BaseFrame.of());
			}}), Pins.of(9), 1)
		);
	}

	@DisplayName("프레임 추가")
	@ParameterizedTest
	@MethodSource
	void addFrame(final Frames frames, final Pins hitPins, final int expectedFrameSize) {
		// given

		// when
		frames.hitPins(hitPins);

		// then
		assertThat(frames.size()).isEqualTo(expectedFrameSize);
	}
}
