package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("프레임들")
class FramesTest {

	// expected - frame index 는 0 부터 시작
	@DisplayName("[성공] 투구")
	@ParameterizedTest
	@CsvSource({
		"2,1",
		"3,1",
		"4,2",
		"5,2",
		"9,4",
		"10,5",
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

	@DisplayName("마지막 프레임 출력 - 피치 0")
	@Test
	void printLastFrame_pitch0() {
		// given
		final Frames frames = initFrames();

		// when

		// then
		assertThat(frames.getValues().get(9).getResult()).isEmpty();
	}

	private Frames initFrames() {
		final List<Frame> frameList = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			frameList.add(new NormalFrame(new Pitch(10)));
		}
		frameList.add(new LastFrame());
		return new Frames(frameList);
	}

	static Stream<Arguments> lastFramePitch1() {
		return Stream.of(
			Arguments.of(new Pitch(10), "X")
		);
	}

	@DisplayName("마지막 프레임 출력 - 피치 1")
	@ParameterizedTest
	@MethodSource("lastFramePitch1")
	void printLastFrame_pitch1(final Pitch pitch, final String expected) {
		// given
		final Frames frames = initFrames();

		// when
		frames.pitch(pitch);

		// then
		assertThat(frames.getValues().get(9).getResult()).isEqualTo(expected);
	}

	static Stream<Arguments> lastFramePitch2() {
		return Stream.of(
			Arguments.of(new Pitch(10), new Pitch(10), "X|X"),
			Arguments.of(new Pitch(5), new Pitch(4), "5|4"),
			Arguments.of(new Pitch(6), new Pitch(4), "6|/")
		);
	}

	@DisplayName("마지막 프레임 출력 - 피치 2")
	@ParameterizedTest
	@MethodSource("lastFramePitch2")
	void printLastFrame_pitch2(final Pitch first, final Pitch second, final String expected) {
		// given
		final Frames frames = initFrames();

		// when
		frames.pitch(first);
		frames.pitch(second);

		// then
		assertThat(frames.getValues().get(9).getResult()).isEqualTo(expected);
	}
}
