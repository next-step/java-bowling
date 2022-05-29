package bowling.frame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("마지막 프레임 테스트")
class EndFrameTest {

	@Test
	void 마지막_프레임_동등성_테스트() {
		assertThat(new EndFrame()).isEqualTo(new EndFrame());
	}

	@Test
	void 두_번의_시도가_오픈_이면_종료() {
		EndFrame endFrame = new EndFrame();
		endFrame.throwBowl(1);
		endFrame.throwBowl(1);

		assertThat(endFrame.isEnd()).isTrue();
	}

	@ParameterizedTest(name = " {displayName} : {0}-{1}-{2} =>> {3}")
	@CsvSource(
		delimiter = ':',
		value = {
			"1:9:1:스페어와 일반점수",
			"1:9:1:스페어와 스트라이크",
			"10:1:1:스트라이크와 오픈",
			"10:1:9:스트라이크와 스페어",
			"10:10:1:스트라이크와 스트라이크와 모든 점수1",
			"10:10:10:스트라이크와 스트라이크와 모든 점수2"
		}
	)
	void 세_번_시도하는_경우_종료(int first, int second, int third, String desc) {
		EndFrame endFrame = new EndFrame();
		endFrame.throwBowl(first);
		endFrame.throwBowl(second);
		endFrame.throwBowl(third);

		assertThat(endFrame.isEnd()).isTrue();
	}

	@Test
	void 마지막_프레임은_다음_프레임_생성하지_않음() {
		assertThat(new EndFrame().nextFrame()).isNotPresent();
	}

	@Test
	void 마지막_프레임의_프레임_숫자는_최대_프레임_숫자와_같음() {
		assertThat(new EndFrame().number()).isEqualTo(Frame.MAX_FRAME_NUMBER);
	}

}