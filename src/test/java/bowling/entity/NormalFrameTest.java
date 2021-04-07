package bowling.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class NormalFrameTest {

	@Test
	void test() {

		NormalFrame normalFrame = NormalFrame.ofNext(10, null);
		assertThat(normalFrame.isLast())
			.isEqualTo(true);

		NormalFrame normalFrame1 = NormalFrame.ofNext(8, null);
		NormalFrame normalFrame2 = NormalFrame.ofNext(2, normalFrame1);
		assertThat(normalFrame2.isLast())
			.isEqualTo(true);

		NormalFrame normalFrame3 = NormalFrame.ofNext(8, null);
		assertThat(normalFrame3.isLast())
			.isEqualTo(false);
	}


	@ParameterizedTest
	@DisplayName("normalFrame이 마지막인 케이스")
	@CsvSource(value = {"1,9", "2,8", "3,7", "4,6", "5,5", "6,4", "7,3", "8,2", "9,1"})
	void normalFrame_last_test(int prev, int next) {

		NormalFrame normalFrame = NormalFrame.ofNext(prev, null);
		assertThat(normalFrame.isLast())
			.isEqualTo(false);


		NormalFrame normalFrame2 = NormalFrame.ofNext(next, normalFrame);
		assertThat(normalFrame2.isLast())
			.isEqualTo(true);
	}
}
