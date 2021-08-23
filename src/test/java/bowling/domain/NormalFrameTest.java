package bowling.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.exception.InvalidPinCountException;

@DisplayName("보통 프레임")
class NormalFrameTest {

	static Stream<Arguments> pitchResults() {
		return Stream.of(
			Arguments.of(0, 10),
			Arguments.of(1, 9),
			Arguments.of(9, 1),
			Arguments.of(10, 0)
		);
	}

	@DisplayName("[성공] 생성")
	@ParameterizedTest
	@MethodSource("pitchResults")
	void create(final int first, final int second) {
		// given
		final NormalFrame normalFrame = new NormalFrame();

		// when
		normalFrame.pitch(first);
		normalFrame.pitch(second);

		// then
		assertThat(normalFrame).isNotNull();
	}

	static Stream<Arguments> invalidPitchResults() {
		return Stream.of(
			Arguments.of(1, 10),
			Arguments.of(5, 9),
			Arguments.of(10, 1)
		);
	}

	@DisplayName("[실패] 생성 - 유효하지 않은 투구 결과")
	@ParameterizedTest
	@MethodSource("invalidPitchResults")
	void create_invalidPitchResult(final int first, final int second) {
		// given
		final NormalFrame normalFrame = new NormalFrame();
		normalFrame.pitch(first);

		// when
		assertThrows(InvalidPinCountException.class, () -> normalFrame.pitch(second));

		// then
	}

	static Stream<Arguments> isEnd() {
		return Stream.of(
			Arguments.of(new NormalFrame(new Pitch(1)), false),
			Arguments.of(new NormalFrame(new Pitch(10)), true),
			Arguments.of(new NormalFrame(new Pitch(5), new Pitch(5)), true)
		);
	}

	@DisplayName("완료된 프레임")
	@ParameterizedTest
	@MethodSource("isEnd")
	void isEnd(final NormalFrame normalFrame, final boolean expected) {
		// given

		// when
		assertThat(normalFrame.isEnd()).isEqualTo(expected);

		// then
	}
}
