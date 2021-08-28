package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("점수")
class ScoreTest {

	static Stream<Arguments> pitches() {
		return Stream.of(
			Arguments.of(createPitches(10), Collections.emptyList()),
			Arguments.of(createPitches(5, 4), createScores(9))
		);
	}

	@DisplayName("점수 계산")
	@ParameterizedTest
	@MethodSource("pitches")
	void calculate(final List<Pitch> pitches, final List<Score> expected) {
		// given
		final Frames frames = new Frames();

		// when
		pitches.forEach(frames::pitch);
		frames.calculateScore();

		// then
		assertThat(frames.getScores()).isEqualTo(expected);
	}

	private static List<Pitch> createPitches(final int... pitchCounts) {
		return Arrays.stream(pitchCounts)
			.mapToObj(Pitch::new)
			.collect(Collectors.toList());
	}

	private static List<Score> createScores(final int... scores) {
		return Arrays.stream(scores)
			.mapToObj(Score::new)
			.collect(Collectors.toList());
	}
}
