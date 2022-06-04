package bowling.frame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.score.Score;

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

	@ParameterizedTest(name = "{0}-{1}-{2} =>> {3}")
	@CsvSource(
		delimiter = ':',
		value = {
			"1:9:1:스페어와 일반점수",
			"1:9:10:스페어와 스트라이크",
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
	void 두_번의_시도로_오픈으로_종료_후_투구하는_경우_예외() {
		EndFrame endFrame = new EndFrame();
		endFrame.throwBowl(1);
		endFrame.throwBowl(1);

		assertThatIllegalStateException().isThrownBy(
			() -> endFrame.throwBowl(1)
		);
	}

	@ParameterizedTest(name = "{0}-{1}-{2} =>> {3}")
	@CsvSource(
		delimiter = ':',
		value = {
			"1:9:1:스페어와 일반점수",
			"1:9:10:스페어와 스트라이크",
			"10:1:1:스트라이크와 오픈",
			"10:1:9:스트라이크와 스페어",
			"10:10:1:스트라이크와 스트라이크와 모든 점수1",
			"10:10:10:스트라이크와 스트라이크와 모든 점수2"
		}
	)
	void 세_번_시도로_종료하고_투구하는_경우_예외(int first, int second, int third, String desc) {

		EndFrame endFrame = new EndFrame();

		endFrame.throwBowl(first);
		endFrame.throwBowl(second);
		endFrame.throwBowl(third);

		assertThatIllegalStateException().isThrownBy(
			() -> endFrame.throwBowl(1)
		);
	}

	@Test
	void 마지막_프레임은_다음_프레임_생성하지_않음() {
		assertThat(new EndFrame().nextFrame()).isNotPresent();
	}

	@Test
	void 마지막_프레임의_프레임_숫자는_최대_프레임_숫자와_같음() {
		assertThat(new EndFrame().number()).isEqualTo(Frame.MAX_FRAME_NUMBER);
	}

	@Test
	void 두_번의_시도가_오픈일_떄_점수계산() {
		EndFrame endFrame = new EndFrame();
		endFrame.throwBowl(1);
		endFrame.throwBowl(1);

		assertThat(endFrame.score()).isEqualTo(Score.of(2, 0));
	}

	@ParameterizedTest(name = "{0}-{1}-{2} =>> {3}")
	@CsvSource(
		delimiter = ':',
		value = {
			"1:9:1:11:스페어와 일반점수",
			"1:9:10:20:스페어와 스트라이크",
			"10:1:1:12:스트라이크와 오픈",
			"10:1:9:20:스트라이크와 스페어",
			"10:10:1:21:스트라이크와 스트라이크와 모든 점수1",
			"10:10:10:30:스트라이크와 스트라이크와 모든 점수2"
		}
	)
	void 세_번_시도로_종료하고_투구하는_경우_점수계산(int first, int second, int third, int score, String desc) {

		EndFrame endFrame = new EndFrame();

		endFrame.throwBowl(first);
		endFrame.throwBowl(second);
		endFrame.throwBowl(third);

		assertThat(endFrame.score()).isEqualTo(Score.of(score, 0));
	}

}