package bowling.Comparator;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.model.frame.NormalFrame;

class FrameComparatorTest {

	@ParameterizedTest
	@DisplayName("프레임을 프레임 번호로 정렬한다.")
	@CsvSource(value = {"4,2,1", "1,2,-1"})
	public void compareFrame(int firstFrameNumber, int secondFrameNumber, int compareNumber) {
		NormalFrame firstFrame = new NormalFrame(firstFrameNumber);
		NormalFrame secondFrame = new NormalFrame(secondFrameNumber);
		FrameComparator frameComparator = new FrameComparator();
		int result = frameComparator.compare(firstFrame, secondFrame);

		assertThat(result).isEqualTo(compareNumber);
	}
}