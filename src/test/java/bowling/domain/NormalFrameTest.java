package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NormalFrameTest {

	@DisplayName("1~9프레임 정상 생성 테스트")
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
	void normalFrameMakeTest(int frameNumber) {
		NormalFrame normalFrame = new NormalFrame(frameNumber);
		assertThat(normalFrame).isEqualTo(new NormalFrame(frameNumber));
	}

	@DisplayName("비정상 프레임 생성 테스트")
	@ParameterizedTest
	@ValueSource(ints = {-1, 10})
	void invalidNormalFrameMakeTest(int frameNumber) {
		assertThatThrownBy(() -> {
			new NormalFrame(frameNumber);
		}).isInstanceOf(IllegalArgumentException.class).hasMessage("Normal Frame은 1~9 프레임까지만 가능합니다.");
	}

	@Test
	@DisplayName("MISS 테스트")
	void normalFramePlayTest() {
		NormalFrame normalFrame = new NormalFrame(1);
		normalFrame.bowl(5);
		normalFrame.bowl(4);
		assertThat(normalFrame.pinStatus().firstPin()).isEqualTo(5);
		assertThat(normalFrame.pinStatus().secondPin()).isEqualTo(4);
		assertThat(Score.score(normalFrame.pinStatus().secondPin(), normalFrame.pinStatus().firstPin(), 2)).isEqualTo(Score.MISS);
	}

	@Test
	@DisplayName("STRIKE 테스트")
	void normalFramePlayTest2() {
		NormalFrame normalFrame = new NormalFrame(1);
		normalFrame.bowl(10);
		assertThat(normalFrame.pinStatus().firstPin()).isEqualTo(10);
		assertThat(Score.score(normalFrame.pinStatus().firstPin(), 0, 1)).isEqualTo(Score.STRIKE);
	}

	@Test
	@DisplayName("SPARE 테스트")
	void normalFramePlayTest3() {
		Frame normalFrame = new NormalFrame(1);
		normalFrame.bowl(5);
		normalFrame.bowl(5);
		assertThat(normalFrame.pinStatus().firstPin()).isEqualTo(5);
		assertThat(normalFrame.pinStatus().secondPin()).isEqualTo(5);
		assertThat(Score.score(normalFrame.pinStatus().secondPin(), normalFrame.pinStatus().firstPin(), 2)).isEqualTo(Score.SPARE);
	}

	@Test
	@DisplayName("GUTTER 테스트")
	void normalFramePlayTest4() {
		Frame normalFrame = new NormalFrame(1);
		normalFrame.bowl(0);
		normalFrame.bowl(0);
		assertThat(normalFrame.pinStatus().firstPin()).isEqualTo(0);
		assertThat(normalFrame.pinStatus().secondPin()).isEqualTo(0);
		assertThat(Score.score(normalFrame.pinStatus().secondPin(), normalFrame.pinStatus().firstPin(), 2)).isEqualTo(Score.GUTTER);
	}
}
