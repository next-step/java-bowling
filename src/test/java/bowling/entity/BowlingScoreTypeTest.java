package bowling.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BowlingScoreTypeTest {

	@Test
	void strike_test() {
		// when
		BowlingScoreType bowlingScoreType = BowlingScoreType.getInstance(new FirstWard(10), null);

		// then
		assertThat(bowlingScoreType)
			.isEqualTo(BowlingScoreType.STRIKE);
	}

	@ParameterizedTest
	@CsvSource(value = {"0, 10", "1,9", "2,8", "3,7", "4,6", "5,5", "6,4", "7,3", "8,2", "9,1", "10,0"})
	void spare_test(int first, int second) {
		// when
		BowlingScoreType bowlingScoreType = BowlingScoreType.getInstance(new FirstWard(first), new SecondWard(second));

		// then
		assertThat(bowlingScoreType)
			.isEqualTo(BowlingScoreType.SPARE);
	}

	@ParameterizedTest
	@CsvSource(value = {"1,0", "2,0", "3,0", "4,0", "5,0", "6,0", "7,0", "8,0", "9,0"})
	void miss_test(int first, int second) {
		// when
		BowlingScoreType bowlingScoreType = BowlingScoreType.getInstance(new FirstWard(first), new SecondWard(second));

		// then
		assertThat(bowlingScoreType)
			.isEqualTo(BowlingScoreType.MISS);
	}

	@Test
	void gutter_test() {
		// when
		BowlingScoreType bowlingScoreType = BowlingScoreType.getInstance(new FirstWard(0), new SecondWard(0));

		// then
		assertThat(bowlingScoreType)
			.isEqualTo(BowlingScoreType.GUTTER);
	}

	@ParameterizedTest
	@CsvSource(value = {"1,2", "2,3", "3,4", "4,5", "1,3"})
	void other_test(int first, int second) {
		// when
		BowlingScoreType bowlingScoreType = BowlingScoreType.getInstance(new FirstWard(first), new SecondWard(second));

		// then
		assertThat(bowlingScoreType)
			.isEqualTo(BowlingScoreType.OTHER);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7})
	void other_test(int first) {
		// when
		BowlingScoreType bowlingScoreType = BowlingScoreType.getInstance(new FirstWard(first), null);

		// then
		assertThat(bowlingScoreType)
			.isEqualTo(BowlingScoreType.OTHER);
	}
}
