package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.exception.GameOverException;

public class FinalFrameTest {

	@Test
	void 새로운_최종_프레임을_만든다() {
		FinalFrame finalFrame = new FinalFrame();
		assertThat(finalFrame).isEqualTo(new FinalFrame());
	}

	@Test
	void 프레임에_공을_두번_던진다() {
		FinalFrame finalFrame = new FinalFrame();
		finalFrame.pitch(1);
		finalFrame.pitch(8);
		assertThat(finalFrame.getPins())
			.containsExactly(new Pin(1), new Pin(8));
	}

	@ParameterizedTest
	@CsvSource(value = {"1, 9, 1, 1, /, 1", "10, 10, 10, X, X, X", "10, 1, 2, X, 1, 2"})
	void 보너스가_있고_프레임에_공을_세번_던진다(int first, int second, int third, String expectFirstSignal, String expectSecondSignal, String expectThirdSignal) {
		FinalFrame finalFrame = new FinalFrame();
		finalFrame.pitch(first);
		finalFrame.pitch(second);
		finalFrame.pitch(third);
		assertThat(finalFrame.getPins())
			.containsExactly(new Pin(first), new Pin(second), new Pin(third));
		assertThat(finalFrame.getScore())
			.containsExactly(expectFirstSignal, expectSecondSignal, expectThirdSignal);
	}

	@Test
	void 보너스가_없는데_추가로_공을_던지면_오류가_나타난다() {
		FinalFrame finalFrame = new FinalFrame();
		finalFrame.pitch(1);
		finalFrame.pitch(8);
		assertThatThrownBy(() -> finalFrame.pitch(10))
			.isInstanceOf(GameOverException.class);
	}

	@Test
	void 프레임에_세번_이상_공을_던지면_오류가_나타난다() {
		FinalFrame finalFrame = new FinalFrame();
		finalFrame.pitch(10);
		finalFrame.pitch(10);
		finalFrame.pitch(10);
		assertThatThrownBy(() -> finalFrame.pitch(10))
			.isInstanceOf(GameOverException.class);
	}

	@Test
	void 프레임에_열개_이상_핀을_넘어뜨리려_하면_오류가_나타난다() {
		FinalFrame finalFrame = new FinalFrame();
		finalFrame.pitch(9);
		assertThatThrownBy(() -> finalFrame.pitch(10))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
