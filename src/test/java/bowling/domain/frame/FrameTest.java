package bowling.domain.frame;

import bowling.domain.DownedPinCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.DownedPinCount.*;
import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

	@DisplayName("객체 생성 및 record 메서드 테스트")
	@Test
	void constructTest() {
		int index = 0;
		Frame frame1 = new Frame(index);
		frame1.roll(DownedPinCount.fromDownCount(1));
		Frame frame2 = new Frame(index);
		frame2.roll(DownedPinCount.fromDownCount(1));
		assertThat(frame1).isEqualTo(frame2);
	}

	@DisplayName("Frame이 종료되었는지 알수 있나 테스트")
	@Test
	void isFrameFinished() {
		int index = 0;
		Frame frame1 = new Frame(index);
		frame1.roll(DownedPinCount.fromDownCount(10));

		Frame frame2 = new Frame(index);
		frame2.roll(DownedPinCount.fromDownCount(5));
		frame2.roll(DownedPinCount.fromDownCount(0));

		assertThat(frame1.isFrameFinished()).isTrue();
		assertThat(frame2.isFrameFinished()).isTrue();
	}
}
