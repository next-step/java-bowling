package bowling.frame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

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
	void 종료_상태이고_마지막_직전_프레임이_아니라면_다음으로_일반프레임_생성() {
		GeneralFrame frame = new GeneralFrame(1) {
			@Override
			public boolean isEnd() {
				return true;
			}
		};

		assertAll(
			() -> assertThat(frame.isEnd()).isTrue(),
			() -> assertThat(frame.number()).isNotEqualTo(Frame.MAX_FRAME_NUMBER - 1),
			() -> assertThat(frame.nextFrame()).isPresent().get().isEqualTo(new GeneralFrame(2))
		);
	}

	@Test
	void 종료_상태이고_마지막_직전_프레임이라면_다음으로_마지막_프레임_생성() {
		GeneralFrame frame = new GeneralFrame(9) {
			@Override
			public boolean isEnd() {
				return true;
			}
		};

		assertAll(
			() -> assertThat(frame.isEnd()).isTrue(),
			() -> assertThat(frame.number()).isEqualTo(Frame.MAX_FRAME_NUMBER - 1),
			() -> assertThat(frame.nextFrame()).isPresent().get().isExactlyInstanceOf(EndFrame.class)
		);
	}

	@Test
	void 종료_상태가_아니면_다음_프레임_생성하지_않음() {
		GeneralFrame frame = new GeneralFrame(1) {
			@Override
			public boolean isEnd() {
				return false;
			}
		};
		frame.throwBowl(2);

		assertAll(
			() -> assertThat(frame.isEnd()).isFalse(),
			() -> assertThat(frame.nextFrame()).isNotPresent()
		);
	}
}