package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinalFrameTest {

	@Test
	@DisplayName("10프레임 정상 생성 테스트")
	void finalFrameMakeTest() {
		Frame finalFrame = new FinalFrame(10);
		assertThat(finalFrame).isEqualTo(new FinalFrame(10));
	}

	@Test
	@DisplayName("10프레임 투구 테스트")
	void finalFrameBowlTest() {
		Frame finalFrame = new FinalFrame(10);
		finalFrame.bowl(5);
		finalFrame.bowl(5);
		finalFrame.bowl(10);
		assertThat(finalFrame.pinStatus().totalPin()).isEqualTo(20);
		assertThat(finalFrame.pinStatus().firstPin()).isEqualTo(5);
		assertThat(finalFrame.pinStatus().secondPin()).isEqualTo(5);
		assertThat(finalFrame.pinStatus().bonusPin()).isEqualTo(10);
	}
}
