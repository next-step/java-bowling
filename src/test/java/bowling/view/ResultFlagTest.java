package bowling.view;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;

@DisplayName("출력 - 프레임 결과")
class ResultFlagTest {

	static Stream<Arguments> pitchCount() {
		return Stream.of(
			Arguments.of(3, "X|X|X"),
			Arguments.of(2, "X|X"),
			Arguments.of(1, "X")
		);
	}

	@DisplayName("출력 - 마지막 프레임")
	@ParameterizedTest
	@MethodSource("pitchCount")
	void generateResultByFrame(final int pitchCount, final String expected) {
		// given
		Frame frame = FinalFrame.of();
		for (int i = 0; i < pitchCount; i++) {
			frame = frame.pitch(10);
		}

		// when
		final String result = ResultFlag.generateResultByFrame(frame, "|");

		// then
		assertThat(result).isEqualTo(expected);
	}
}
