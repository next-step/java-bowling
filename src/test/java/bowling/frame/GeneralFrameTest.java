package bowling.frame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.score.Score;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("일반 프레임 테스트")
class GeneralFrameTest {

	@Test
	void 일반_프레임_동등성_테스트() {
		assertThat(GeneralFrame.initialized(1)).isEqualTo(GeneralFrame.initialized(1));
	}

	@Test
	void 일반_프레임은_최소_번호가_1_부터_생성_가능() {
		assertThatIllegalArgumentException().isThrownBy(
			() -> GeneralFrame.initialized(0)
		);
	}

	@Test
	void 일반_프레임은_최대_마지막_직전_프레임까지만_생성_가능() {
		assertThatIllegalArgumentException().isThrownBy(
			() -> GeneralFrame.initialized(10)
		);
	}

	@Test
	void 종료_상태가_아니면_다음_프레임_생성하지_않음() {
		GeneralFrame frame = GeneralFrame.initialized(1);
		frame.throwBowl(2);

		assertAll(
			() -> assertThat(frame.isEnd()).isFalse(),
			() -> assertThat(frame).extracting("next").isNull(),
			() -> assertThat(frame.nextFrame()).isNotPresent(),
			() -> assertThat(frame).extracting("next").isNull()
		);
	}

	@ParameterizedTest(name = "{0} 점 * {1} 번 투구 : {2}")
	@CsvSource(
		delimiter = ':',
		value = {
			"3:2:오픈",
			"5:2:스페어",
			"10:1:스트라이크"
		}
	)
	void 종료_상태이고_마지막_직전_프레임이_아니라면_다음으로_일반프레임_생성(int point, int tryCount, String result) {
		GeneralFrame frame = GeneralFrame.initialized(1);

		for (int i = 0; i < tryCount; i++) {
			frame.throwBowl(point);
		}

		assertAll(
			() -> assertThat(frame.isEnd()).isTrue(),
			() -> assertThat(frame.number()).isNotEqualTo(Frame.MAX_FRAME_NUMBER - 1),
			() -> assertThat(frame).extracting("next").isNull(),
			() -> assertThat(frame.nextFrame()).isPresent().get().isEqualTo(GeneralFrame.initialized(2)),
			() -> assertThat(frame).extracting("next").isNotNull()
		);
	}

	@ParameterizedTest(name = "{0} 점 * {1} 번 투구 : {2}")
	@CsvSource(
		delimiter = ':',
		value = {
			"3:2:오픈",
			"5:2:스페어",
			"10:1:스트라이크"
		}
	)
	void 종료_상태이고_마지막_직전_프레임이라면_다음으로_마지막_프레임_생성(int point, int tryCount, String result) {
		GeneralFrame frame = GeneralFrame.initialized(9);

		for (int i = 0; i < tryCount; i++) {
			frame.throwBowl(point);
		}

		assertAll(
			() -> assertThat(frame.isEnd()).isTrue(),
			() -> assertThat(frame.number()).isEqualTo(Frame.MAX_FRAME_NUMBER - 1),
			() -> assertThat(frame).extracting("next").isNull(),
			() -> assertThat(frame.nextFrame()).isPresent().get().isExactlyInstanceOf(EndFrame.class),
			() -> assertThat(frame).extracting("next").isNotNull()
		);
	}

	@Test
	void 끝나지_않은_상태면_점수_계산_불가() {
		GeneralFrame generalFrame = GeneralFrame.initialized(1);
		generalFrame.throwBowl(1);

		assertThat(generalFrame.score()).isEqualTo(Score.unavailable());
	}

	@Test
	void 오픈은_점수_계산_가능() {
		GeneralFrame generalFrame = GeneralFrame.initialized(1);
		generalFrame.throwBowl(1);
		generalFrame.throwBowl(1);

		assertThat(generalFrame.score()).isEqualTo(Score.of(2, 0));
	}

	@Test
	void 다음_프레임이_지정되지않으면__보너스_계산_불가() {
		GeneralFrame generalFrame = GeneralFrame.initialized(1);
		generalFrame.throwBowl(10);

		assertThatThrownBy(
			generalFrame::score
		).isExactlyInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 스페어_계산() {
		GeneralFrame generalFrame = GeneralFrame.initialized(1);
		generalFrame.throwBowl(2);
		generalFrame.throwBowl(8);

		Frame firstBonusFrame = generalFrame.nextFrame().get();
		firstBonusFrame.throwBowl(1);

		assertThat(generalFrame.score()).isEqualTo(Score.of(11, 0));
	}

	@Test
	void 스트라이크와_다음프레임_1개_점수_계산() {
		GeneralFrame generalFrame = GeneralFrame.initialized(1);
		generalFrame.throwBowl(10);

		Frame firstBonusFrame = generalFrame.nextFrame().get();
		firstBonusFrame.throwBowl(1);
		firstBonusFrame.throwBowl(1);

		assertThat(generalFrame.score()).isEqualTo(Score.of(12, 0));
	}

	@Test
	void 스트라이크와_다음_두_개의_프레임_계산() {
		GeneralFrame generalFrame = GeneralFrame.initialized(1);
		generalFrame.throwBowl(10);

		Frame firstBonusFrame = generalFrame.nextFrame().get();
		firstBonusFrame.throwBowl(10);

		Frame secondBonusFrame = firstBonusFrame.nextFrame().get();
		secondBonusFrame.throwBowl(10);

		assertThat(generalFrame.score()).isEqualTo(Score.of(30, 0));
	}
}