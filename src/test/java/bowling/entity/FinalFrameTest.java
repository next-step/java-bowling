package bowling.entity;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class FinalFrameTest {

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
	void first(int input) {
		FinalFrame finalFrame = new FinalFrame();

		finalFrame.add(0, input);
		assertThat(finalFrame.isKeepGoing(0))
			.isEqualTo(true);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
	void second_success(int input) {
		FinalFrame finalFrame = new FinalFrame();

		finalFrame.add(0, input);
		finalFrame.add(1, 10 - input);
		assertThat(finalFrame.isKeepGoing(1))
			.isEqualTo(true);
	}

	@Test
	void zero_test() {
		assertThat(FinalFrameString.getString(new ArrayList<>()))
			.isEqualTo(FinalFrameString.ZERO.stringMethod(new ArrayList<>()));
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
	void ONE_test(int input) {
		// given
		List<Ward> wardList = new ArrayList<>();

		// when
		wardList.add(new Ward(input) {
			@Override
			boolean hasNext() {
				return false;
			}
		});

		// then
		assertThat(FinalFrameString.ONE.stringMethod(wardList))
			.isEqualTo("  " + input);
	}

	@Test
	void ONE_strike_test() {
		// given
		List<Ward> wardList = new ArrayList<>();

		// when
		wardList.add(new Ward(10) {
			@Override
			boolean hasNext() {
				return false;
			}
		});

		// then
		assertThat(FinalFrameString.ONE.stringMethod(wardList))
			.isEqualTo("  " + BowlingScoreType.STRIKE.getScore());
	}

	@ParameterizedTest
	@CsvSource(value = {"1,2", "2,3", "3,4", "4,5"})
	void two_others_test(int prev, int current) {
		// given
		FinalFrame finalFrame = new FinalFrame();

		// when
		finalFrame.add(0, prev);
		finalFrame.add(1, current);

		// then
		assertThat(FinalFrameString.TWO.stringMethod(finalFrame.getWards()))
			.isEqualTo(prev + "|" + current);
	}

	@ParameterizedTest
	@CsvSource(value = {"1,9", "2,8", "3,7", "4,6"})
	void two_spare_test(int prev, int current) {
		// given
		FinalFrame finalFrame = new FinalFrame();

		// when
		finalFrame.add(0, prev);
		finalFrame.add(1, current);

		// then
		assertThat(FinalFrameString.TWO.stringMethod(finalFrame.getWards()))
			.isEqualTo(prev + "|" + BowlingScoreType.SPARE.getScore());
	}
	@ParameterizedTest
	@CsvSource(value = {"10,9", "10,8", "10,7", "10,6"})
	@DisplayName("첫번째구 Strke")
	void two_first_spare_test(int prev, int current) {
		// given
		FinalFrame finalFrame = new FinalFrame();

		// when
		finalFrame.add(0, prev);
		finalFrame.add(1, current);

		// then
		assertThat(FinalFrameString.TWO.stringMethod(finalFrame.getWards()))
			.isEqualTo("  "  + BowlingScoreType.STRIKE.getScore() + "|  " + current);
	}
}
