package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.exception.InvalidPlayCountException;

public class FinalFrameTest {

	@Test
	@DisplayName("10프레임 정상 생성 테스트")
	void finalFrameMakeTest() {
		Frame finalFrame = new FinalFrame(10);
		assertThat(finalFrame).isEqualTo(new FinalFrame(10));
	}

	@Test
	@DisplayName("10프레임 투구 테스트 : 스페어처리 후, 마지막 보너스 핀(10개)")
	void finalFrameBowlTest() {
		Frame finalFrame = new FinalFrame(10);
		finalFrame.bowl(5);
		finalFrame.bowl(5);
		finalFrame.bowl(10);
		assertThat(finalFrame.pinStatus().totalPin()).isEqualTo(20);
		assertThat(finalFrame.pinStatus().firstPin()).isEqualTo(5);
		assertThat(finalFrame.pinStatus().secondPin()).isEqualTo(5);
		assertThat(finalFrame.pinStatus().bonusPin()).isEqualTo(10);

		assertThat(Score.score(finalFrame.pinStatus().pinSize(2), finalFrame.pinStatus().pinSize(1),
			finalFrame.pinStatus().pinSize(0), 3)).isEqualTo(Score.STRIKE);

	}

	@Test
	@DisplayName("10프레임 투구 테스트2 : 스페어처리 후, 마지막 보너스 핀")
	void finalFrameBowlTest2() {
		Frame finalFrame = new FinalFrame(10);
		finalFrame.bowl(5);
		finalFrame.bowl(5);
		finalFrame.bowl(5);

		assertThat(Score.score(finalFrame.pinStatus().pinSize(0), 0, 0, 1)).isEqualTo(Score.MISS);
		assertThat(Score.score(finalFrame.pinStatus().pinSize(1), finalFrame.pinStatus().pinSize(0), 0, 2)).isEqualTo(
			Score.SPARE);
		assertThat(Score.score(finalFrame.pinStatus().pinSize(2), finalFrame.pinStatus().pinSize(1),
			finalFrame.pinStatus().pinSize(0), 3)).isEqualTo(Score.MISS);

	}

	@Test
	@DisplayName("10프레임 투구 테스트3 : 스트라이크 기록 후, 스페어 처리(보너스핀 포함)")
	void finalFrameBowlTest3() {
		Frame finalFrame = new FinalFrame(10);
		finalFrame.bowl(10);
		finalFrame.bowl(5);
		finalFrame.bowl(5);

		assertThat(Score.score(finalFrame.pinStatus().pinSize(0), 0, 0, 1)).isEqualTo(Score.STRIKE);
		assertThat(Score.score(finalFrame.pinStatus().pinSize(1), finalFrame.pinStatus().pinSize(0), 0, 2)).isEqualTo(
			Score.MISS);
		assertThat(Score.score(finalFrame.pinStatus().pinSize(2), finalFrame.pinStatus().pinSize(1),
			finalFrame.pinStatus().pinSize(0), 3)).isEqualTo(Score.SPARE);

	}

	@Test
	@DisplayName("10프레임 투구 테스트4 : 더블 기록 후, 보너스 핀")
	void finalFrameBowlTest4() {
		Frame finalFrame = new FinalFrame(10);
		finalFrame.bowl(10);
		finalFrame.bowl(10);
		finalFrame.bowl(5);

		assertThat(Score.score(finalFrame.pinStatus().pinSize(0), 0, 0, 1)).isEqualTo(Score.STRIKE);
		assertThat(Score.score(finalFrame.pinStatus().pinSize(1), finalFrame.pinStatus().pinSize(0), 0, 2)).isEqualTo(
			Score.STRIKE);
		assertThat(Score.score(finalFrame.pinStatus().pinSize(2), finalFrame.pinStatus().pinSize(1),
			finalFrame.pinStatus().pinSize(0), 3)).isEqualTo(Score.MISS);

	}

	@Test
	@DisplayName("10프레임 투구 테스트5 : 스페어 처리 후 마지막 보너스 스트라이크")
	void finalFrameBowlTest5() {
		Frame finalFrame = new FinalFrame(10);
		finalFrame.bowl(0);
		finalFrame.bowl(10);
		finalFrame.bowl(10);

		assertThat(Score.score(finalFrame.pinStatus().pinSize(0), 0, 0, 1)).isEqualTo(Score.GUTTER);
		assertThat(Score.score(finalFrame.pinStatus().pinSize(1), finalFrame.pinStatus().pinSize(0), 0, 2)).isEqualTo(
			Score.SPARE);
		assertThat(Score.score(finalFrame.pinStatus().pinSize(2), finalFrame.pinStatus().pinSize(1),
			finalFrame.pinStatus().pinSize(0), 3)).isEqualTo(Score.STRIKE);

	}

	@Test
	@DisplayName("10프레임 투구 테스트6 : 2회 투구까지 스페어처리 못한 경우")
	void finalFrameBowlTest6() {
		Frame finalFrame = new FinalFrame(10);
		finalFrame.bowl(0);
		finalFrame.bowl(0);

		assertThat(Score.score(finalFrame.pinStatus().pinSize(0), 0, 0, 1)).isEqualTo(Score.GUTTER);
		assertThat(Score.score(finalFrame.pinStatus().pinSize(1), finalFrame.pinStatus().pinSize(0), 0, 2)).isEqualTo(
			Score.GUTTER);

	}

	@Test
	@DisplayName("10프레임 투구 테스트 : 스페어 처리하지 못했는데, 보너스 핀까지 시도하는 경우 에러")
	void invalidFinalFrameBowlTest() {
		assertThatThrownBy(() -> {
			Frame finalFrame = new FinalFrame(10);
			finalFrame.bowl(0);
			finalFrame.bowl(5);
			finalFrame.bowl(10);
		}).isInstanceOf(InvalidPlayCountException.class).hasMessage("횟수를 초과했습니다.");
	}

	@Test
	@DisplayName("10프레임 투구 테스트 : 횟수 초과2")
	void invalidFinalFrameBowlTest2() {
		assertThatThrownBy(() -> {
			Frame finalFrame = new FinalFrame(10);
			finalFrame.bowl(0);
			finalFrame.bowl(0);
			finalFrame.bowl(10);
		}).isInstanceOf(InvalidPlayCountException.class).hasMessage("횟수를 초과했습니다.");
	}
}
